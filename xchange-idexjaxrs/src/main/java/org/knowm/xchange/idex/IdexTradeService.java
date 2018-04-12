package org.knowm.xchange.idex;


import org.apache.commons.codec.binary.Hex;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.dto.trade.LimitOrder.Builder;
import org.knowm.xchange.idex.IdexExchange.Companion.IdexCurrencyMeta;
import org.knowm.xchange.idexjaxrs.dto.*;
import org.knowm.xchange.idexjaxrs.service.*;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import si.mazi.rescu.RestProxyFactory;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.knowm.xchange.dto.Order.OrderType.ASK;
import static org.knowm.xchange.dto.Order.OrderType.BID;
import static org.knowm.xchange.idex.IdexExchange.Companion.getCurrencyPair;
import static org.knowm.xchange.idex.IdexExchange.Companion.safeParse;
import static org.knowm.xchange.idex.IdexSignature.generateSignature;
import static org.web3j.crypto.Sign.SignatureData;

public class IdexTradeService implements TradeService {
    private final IdexExchange idexExchange;

    public IdexTradeService(IdexExchange idexExchange) {

        this.idexExchange = idexExchange;
    }

    @Override
    public OpenOrders getOpenOrders() {
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, getIdexExchange().getExchangeSpecification().getSslUri());

        OpenOrders ret = null;
        try {

            ReturnOpenOrdersResponse openOrdersResponse = proxy.openOrders(new OpenOrdersReq().address(getIdexExchange().getExchangeSpecification().getApiKey()));

            ret = new OpenOrders(
                    openOrdersResponse.stream().map(responseInner -> {
                        CurrencyPair currencyPair;
                        OrderType orderType = responseInner.getType() == IdexBuySell.BUY ? BID : ASK;

                        {

                            String market = responseInner.getMarket();
                            currencyPair = getCurrencyPair(market);
                        }
                        return new Builder(orderType, currencyPair)
                                .limitPrice(safeParse(responseInner.getPrice()))
                                .originalAmount(safeParse(responseInner.getAmount()))
                                .id(responseInner.getOrderHash())
                                .build();
                    }).collect(Collectors.toList())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) {
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
        return null;
    }


    @Override
    public boolean cancelOrder(String s) {
        CancelApi proxy = RestProxyFactory.createProxy(CancelApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
        return false;
    }

    @Override
    public boolean cancelOrder(CancelOrderParams cancelOrderParams) {
        CancelApi proxy = RestProxyFactory.createProxy(CancelApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
        return false;
    }

    @Override
    public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) {
        ReturnTradeHistoryApi proxy = RestProxyFactory.createProxy(ReturnTradeHistoryApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public Collection<Order> getOrder(String... strings) {
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public OpenOrdersParams createOpenOrdersParams() {


        return null;
    }

    @Override
    public void verifyOrder(LimitOrder limitOrder) {
        throw new UnsupportedOperationException("Idex API doesn't support verify order");

    }

    @Override
    public void verifyOrder(MarketOrder marketOrder) {
        throw new UnsupportedOperationException("Idex API doesn't support verify order");

    }

    @Override
    public TradeHistoryParams createTradeHistoryParams() {

        return null;
    }

    @Override
    public String placeMarketOrder(MarketOrder marketOrder) {
        throw new UnsupportedOperationException("Idex API doesn't support market order");
    }

    @Override
    public String placeStopOrder(StopOrder stopOrder) {

        throw new UnsupportedOperationException("Idex API doesn't support stop order");
    }

    /**
     * returns OrderHash so you can fetch it and cancel it...  but there is a OrderNumber that you can intercept if you need to.
     */
    @Override
    public String placeLimitOrder(LimitOrder placeOrder) {
        OrderType type = placeOrder.getType();
        Currency baseCurrency = placeOrder.getCurrencyPair().base;
        Currency counterCurrency = placeOrder.getCurrencyPair().counter;
        BigDecimal originalAmount = placeOrder.getOriginalAmount();
        BigDecimal limitPrice = placeOrder.getLimitPrice();
        OrderReq orderReq = createNormalizedLimitOrderReq(baseCurrency, counterCurrency, type, limitPrice, originalAmount, null, null,
                null);
        try {
            RestProxyFactory.createProxy(OrderApi.class, getIdexExchange().getExchangeSpecification().getSslUri()).order(orderReq).getOrderHash();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    @NotNull
    public final OrderReq createNormalizedLimitOrderReq( @NotNull Currency baseCurrency, @NotNull Currency counterCurrency, @NotNull OrderType type, @NotNull BigDecimal limitPrice, @NotNull BigDecimal originalAmount, String contractAddress,  BigInteger nonce,BigInteger expires) {
        nonce = nonce == null ? BigInteger.valueOf(getIdexExchange().getNonceFactory().createValue()) : nonce;
        contractAddress = contractAddress == null ? contractAddress().getAddress() : contractAddress;
        expires = expires == null ? BigInteger.valueOf(100000) : expires;
        getIdexExchange().getExchangeMetaData().getCurrencies().get(baseCurrency);
        getIdexExchange().getExchangeMetaData().getCurrencies().get(counterCurrency);
        List<Currency> listOfCurrencies = asList(baseCurrency,/*OMG*/ counterCurrency/*ETH*/);
        if (type == ASK) Collections.reverse(listOfCurrencies);

        IdexCurrencyMeta buy_currency = (IdexCurrencyMeta) getIdexExchange().getExchangeMetaData().getCurrencies().get(listOfCurrencies.get(0));
        IdexCurrencyMeta sell_currency = (IdexCurrencyMeta) getIdexExchange().getExchangeMetaData().getCurrencies().get(listOfCurrencies.get(1));
        BigDecimal divide = originalAmount.divide(limitPrice, MathContext.DECIMAL128);
        BigDecimal amount_buy = divide.multiply(new BigDecimal("1e" + buy_currency.getDecimals()), MathContext.DECIMAL128);
        BigDecimal amount_sell = originalAmount.multiply(new BigDecimal("1e" + sell_currency.getDecimals()), MathContext.DECIMAL128);
        String buyc = buy_currency.getAddress();
        String sellc = sell_currency.getAddress();
        List<List<String>> hash_data = asList(
                asList("contractAddress", contractAddress, "address"),
                asList("tokenBuy", buyc, "address"),
                asList("amountBuy", amount_buy.toString(), "uint256"),
                asList("tokenSell", sellc, "address"),
                asList("amountSell", amount_sell.toString(), "uint256"),
                asList("expires", "" + expires, "uint256"),
                asList("nonce", "" + nonce, "uint256"),
                asList("address", "" + getApiKey(), "address")
        );

        SignatureData sig = generateSignature(getIdexExchange().getExchangeSpecification().getSecretKey(), hash_data);
        byte v = sig.getV();
        byte[] r = sig.getR();
        byte[] s = sig.getS();
        OrderReq orderReq = new OrderReq()
                .address(getApiKey())
                .nonce(nonce)
                .tokenBuy(buyc)
                .amountBuy(amount_buy.toString())
                .tokenSell(sellc)
                .amountSell(amount_sell.toString())
                .expires(expires)
                .r("0x" + new String(Hex.encodeHex(r)))
                .s("0x" + new String(Hex.encodeHex(s)))
                .v(BigInteger.valueOf(v & 0xffl));
        return orderReq;
    }

      ReturnContractAddressResponse contractAddress() {
        try {
            ReturnContractAddressApi proxy = RestProxyFactory.createProxy(ReturnContractAddressApi.class, getIdexExchange().getExchangeSpecification().getSslUri());
            return proxy.contractAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exchange getIdexExchange() {
        return idexExchange;
    }

    public String getApiKey() {
        return getIdexExchange().getExchangeSpecification().getApiKey();

    }

}
