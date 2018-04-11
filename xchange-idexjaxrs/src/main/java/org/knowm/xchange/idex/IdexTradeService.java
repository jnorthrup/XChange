package org.knowm.xchange.idex;


import org.apache.commons.codec.binary.Hex;
import org.jetbrains.annotations.NotNull;
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
import org.web3j.crypto.Sign.SignatureData;
import si.mazi.rescu.RestProxyFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.knowm.xchange.dto.Order.OrderType.ASK;
import static org.knowm.xchange.dto.Order.OrderType.BID;
import static org.knowm.xchange.idex.IdexExchange.Companion.getCurrencyPair;
import static org.knowm.xchange.idex.IdexExchange.Companion.safeParse;

public class IdexTradeService implements TradeService {
    private final IdexExchange idexExchange;

    public IdexTradeService(IdexExchange idexExchange) {

        this.idexExchange = idexExchange;
    }

    @Override
    public OpenOrders getOpenOrders() {
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, idexExchange.getExchangeSpecification().getSslUri());

        OpenOrders ret = null;
        try {

            ReturnOpenOrdersResponse openOrdersResponse = proxy.openOrders(new OpenOrdersReq().address(idexExchange.getExchangeSpecification().getApiKey()));

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
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, idexExchange.getExchangeSpecification().getSslUri());
        return null;
    }


    @Override
    public boolean cancelOrder(String s) {
        CancelApi proxy = RestProxyFactory.createProxy(CancelApi.class, idexExchange.getExchangeSpecification().getSslUri());
        return false;
    }

    @Override
    public boolean cancelOrder(CancelOrderParams cancelOrderParams) {
        CancelApi proxy = RestProxyFactory.createProxy(CancelApi.class, idexExchange.getExchangeSpecification().getSslUri());
        return false;
    }

    @Override
    public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) {
        ReturnTradeHistoryApi proxy = RestProxyFactory.createProxy(ReturnTradeHistoryApi.class, idexExchange.getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public Collection<Order> getOrder(String... strings) {
        ReturnOpenOrdersApi proxy = RestProxyFactory.createProxy(ReturnOpenOrdersApi.class, idexExchange.getExchangeSpecification().getSslUri());
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

    @Override
    public String placeLimitOrder(LimitOrder placeOrder) {
        OrderApi proxy = RestProxyFactory.createProxy(OrderApi.class, idexExchange.getExchangeSpecification().getSslUri());

        OrderType type = placeOrder.getType();
        Currency baseCurrency = placeOrder.getCurrencyPair().base;
        Currency counterCurrency = placeOrder.getCurrencyPair().counter;
        BigDecimal originalAmount = placeOrder.getOriginalAmount();
        BigDecimal limitPrice = placeOrder.getLimitPrice();
        int var8 = 100000;
        Object var16 = null;
        Object var15 = null;
        BigInteger var10000 = BigInteger.valueOf((long) var8);
        BigInteger var17 = var10000;
        OrderReq orderReq = IdexTradeService.createNormalizedLimitOrderReq$default(this, baseCurrency, counterCurrency, type, limitPrice, originalAmount, (String) var15, (BigInteger) var16, var17, 96, null);

        OrderResponse var18 = null;
        try {
            var18 = proxy.order(orderReq);
//            Intrinsics.checkExpressionValueIsNotNull(var18, "order(orderReq)");
            String var19 = var18.getOrderHash();
//            Intrinsics.checkExpressionValueIsNotNull(var19, "order(orderReq).orderHash");
            return var19;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @NotNull
    public final OrderReq createNormalizedLimitOrderReq(@NotNull Currency baseCurrency, @NotNull Currency counterCurrency, @NotNull OrderType type, @NotNull BigDecimal limitPrice, @NotNull BigDecimal originalAmount, @NotNull String contractAddress, @NotNull BigInteger nonce, @NotNull BigInteger expires) {
//        Intrinsics.checkParameterIsNotNull(baseCurrency, "baseCurrency");
//        Intrinsics.checkParameterIsNotNull(counterCurrency, "counterCurrency");
//        Intrinsics.checkParameterIsNotNull(type, "type");
//        Intrinsics.checkParameterIsNotNull(limitPrice, "limitPrice");
//        Intrinsics.checkParameterIsNotNull(originalAmount, "originalAmount");
//        Intrinsics.checkParameterIsNotNull(contractAddress, "contractAddress");
//        Intrinsics.checkParameterIsNotNull(nonce, "nonce");
//        Intrinsics.checkParameterIsNotNull(expires, "expires");
        idexExchange.getExchangeMetaData().getCurrencies().get(baseCurrency);
        idexExchange.getExchangeMetaData().getCurrencies().get(counterCurrency);
        List c = asList(baseCurrency, counterCurrency);
        if (Objects.equals(type, ASK)) {
            Collections.reverse (c);
        }

        Object var10000 = idexExchange.getExchangeMetaData().getCurrencies().get(c.get(0));
        IdexCurrencyMeta buy_currency = (IdexCurrencyMeta)var10000;
        var10000 = idexExchange.getExchangeMetaData().getCurrencies().get(c.get(1));
        IdexCurrencyMeta sell_currency = (IdexCurrencyMeta)var10000;
        BigDecimal divide = originalAmount.divide(limitPrice, MathContext.DECIMAL128);
        StringBuilder var10001 = (new StringBuilder()).append("1e");
        BigInteger amount_sell = buy_currency.getDecimals();
        StringBuilder var26 = var10001;
        BigDecimal var27 = new BigDecimal(amount_sell);
        String var32 = var26.append(var27).toString();
        BigDecimal var40 = new BigDecimal(var32);
        BigInteger amount_buy = divide.multiply(var40, MathContext.DECIMAL128).toBigInteger();
        var10001 = (new StringBuilder()).append("1e");
        BigInteger var15 = sell_currency.getDecimals();
        var26 = var10001;
        var27 = new BigDecimal(var15);
        String buyc = var26.append(var27).toString();
        var40 = new BigDecimal(buyc);
        amount_sell = originalAmount.multiply(var40, MathContext.DECIMAL128).toBigInteger();
        buyc = buy_currency.getAddress();
        String sellc = sell_currency.getAddress();
        List[] var34 = {asList("contractAddress", contractAddress, "address"), asList("tokenBuy", buyc, "address"), null, null, null, null, null, null};
        String[] var10003 = {"amountBuy", null, null};
        String var10006 = amount_buy.toString();
//                Intrinsics.checkExpressionValueIsNotNull(var10006, "amount_buy.toString()");
        var10003[1] = var10006;
        var10003[2] = "uint256";
        var34[2] = asList(var10003);
        var34[3] = asList("tokenSell", sellc, "address");
        var10003 = new String[]{"amountSell", null, null};
        var10006 = amount_sell.toString();
//                Intrinsics.checkExpressionValueIsNotNull(var10006, "amount_sell.toString()");
        var10003[1] = var10006;
        var10003[2] = "uint256";
        var34[4] = asList(var10003);
        var34[5] = asList("expires", "" + expires, "uint256");
        var34[6] = asList("nonce", "" + nonce, "uint256");
        var10003 = new String[]{"address", null, null};
        byte var30 = 1;
        String[] var29 = var10003;
        String[] var28 = var10003;
        byte var42 = 7;
        List[] var41 = var34;
        List[] var25 = var34;
        String var31 = getIdexExchange().getExchangeSpecification().getApiKey();
        var29[var30] = var31;
        var28[2] = "address";
        var41[var42] = asList(var28);
        List hash_data = asList( var25);
        String var37 = idexExchange.getExchangeSpecification().getSecretKey();
        SignatureData var36 =  IdexSignature.generateSignature(var37, hash_data);


        SignatureData sig = var36;
        byte v = sig.getV();
        byte[] r = sig.getR();
        byte[] s = sig.getS();
        OrderReq var39 = new OrderReq();
        String var43 = idexExchange .getExchangeSpecification().getApiKey();
        var39 = var39.address(var43).nonce(nonce).tokenBuy(buyc).amountBuy(amount_buy.toString()).tokenSell(sellc).amountSell(amount_sell.toString()).expires(expires).r("0x" + Hex.encodeHex(r)).s("0x" + Hex.encodeHex (s));
        BigInteger var38 = BigInteger.valueOf((long)v);
        BigInteger var44 = var38;
        OrderReq orderReq = var39.v(var44);
        return orderReq;
    }

    // $FF: synthetic method
    // $FF: bridge method
    @NotNull
    public static OrderReq createNormalizedLimitOrderReq$default(IdexTradeService var0, Currency var1, Currency var2, OrderType var3, BigDecimal var4, BigDecimal var5, String var6, BigInteger var7, BigInteger var8, int var9, Object var10) {
        if ((var9 & 32) != 0) {
            ReturnContractAddressResponse var10000 = var0.contractAddress();
//            Intrinsics.checkExpressionValueIsNotNull(var10000, "contractAddress()");
            String var12 = var10000.getAddress();
//            Intrinsics.checkExpressionValueIsNotNull(var12, "contractAddress().address");
            var6 = var12;
        }

        BigInteger var13;
        if ((var9 & 64) != 0) {
            var13 = BigInteger.valueOf(var0.idexExchange.getNonceFactory().createValue());
//            Intrinsics.checkExpressionValueIsNotNull(var13, "idexServerNonce");
            var7 = var13;
        }

        if ((var9 & 128) != 0) {
            int var11 = 100000;
            var13 = BigInteger.valueOf((long)var11);
//            Intrinsics.checkExpressionValueIsNotNull(var13, "BigInteger.valueOf(this.toLong())");
            var8 = var13;
        }

        return var0.createNormalizedLimitOrderReq(var1, var2, var3, var4, var5, var6, var7, var8);
    }

      ReturnContractAddressResponse contractAddress() {
        try {
            ReturnContractAddressApi proxy = RestProxyFactory.createProxy(ReturnContractAddressApi.class, idexExchange.getExchangeSpecification().getSslUri());
            return proxy.contractAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exchange getIdexExchange() {
        return idexExchange;
    }

}
