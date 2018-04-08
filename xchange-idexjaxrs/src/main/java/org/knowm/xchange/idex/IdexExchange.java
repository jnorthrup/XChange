package org.knowm.xchange.idex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder.Builder;
import org.knowm.xchange.idexjaxrs.dto.*;
import org.knowm.xchange.idexjaxrs.service.ReturnOrderBookApi;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.idexjaxrs.service.ReturnTradeHistoryApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

public class IdexExchange extends BaseExchange {


    @Override
    protected void initServices() {
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return null;
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {

        return new ExchangeSpecification(IdexExchange.class) {
            @Override
            public String getExchangeClassName() {
                return getClass().getCanonicalName();
            }

            @Override
            public String getExchangeName() {
                return "idex";
            }


            @Override
            public String getSslUri() {
                return Companion.IDEX_API;
            }
        };
    }

    @Override
    public MarketDataService getMarketDataService() {
        return new IdexMarketDataService();
    }

    public enum Companion {
        ;
        public static final String IDEX_API = System.getProperty("xchange.idex.api", "https://api.idex.market/");

        public static BigDecimal safeParse(String s) {
            BigDecimal ret = null;
            try {
                ret = new BigDecimal(s);
            } catch (Exception e) {
                // e.printStackTrace();
            }
            return ret;
        }

        public static String cpairString(CurrencyPair currencyPair) {
            return currencyPair.base.getSymbol() + "_" + currencyPair.counter.getSymbol();
        }

        public static void main(String[] args) throws IOException {
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
            CurrencyPair currencyPair = new CurrencyPair("ETH", "OMG");
            MarketDataService marketDataService = exchange.getMarketDataService();
            Ticker ticker = marketDataService.getTicker(currencyPair);
            System.out.println(ticker);
            Trades trades = marketDataService.getTrades(currencyPair);
            System.err.println(Arrays.deepToString(new Object[]{trades}));
        }
    }

    public class IdexMarketDataService implements MarketDataService {

        @Override
        public Ticker getTicker(CurrencyPair currencyPair, Object... args) {

            ReturnTickerApi proxy =
                    RestProxyFactory.createProxy(ReturnTickerApi.class, IdexExchange.this.exchangeSpecification.getSslUri());
            Ticker ret = null;

            Market market = new Market();
            String market1 = Companion.cpairString(currencyPair);
            Market market2 = market.market(market1);
            ReturnTickerResponse ticker = null;
            try {
                ticker = proxy.ticker(market2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.err.println(ticker);
            ret =
                    new Ticker.Builder()
                            .currencyPair(currencyPair)
                            .last(Companion.safeParse(ticker.getLast()))
                            .ask(Companion.safeParse(ticker.getLowestAsk()))
                            .bid(Companion.safeParse(ticker.getHighestBid()))
                            .volume(Companion.safeParse(ticker.getBaseVolume()))
                            .quoteVolume(Companion.safeParse(ticker.getQuoteVolume()))
                            .high(Companion.safeParse(ticker.getHigh()))
                            .low(Companion.safeParse(ticker.getLow()))
                            .build();

            return ret;
        }

        @Override
        public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) {
            ReturnOrderBookApi proxy1 =
                    RestProxyFactory.createProxy(
                            ReturnOrderBookApi.class, getDefaultExchangeSpecification().getSslUri());
            OrderBook ret = null;
            try {
                ReturnOrderBookResponse returnOrderBookResponse =
                        proxy1.orderBook(new OrderBookReq().market(Companion.cpairString(currencyPair)));
                ret =
                        new OrderBook(
                                new Date(),
                                returnOrderBookResponse
                                        .getAsks()
                                        .stream()
                                        .map(
                                                ask -> {
                                                    BigDecimal limitPrice = Companion.safeParse(ask.getPrice());
                                                    BigDecimal originalAmount = Companion.safeParse(ask.getAmount());
                                                    String orderHash = ask.getOrderHash();
                                                    return new Builder(OrderType.ASK, currencyPair)
                                                            .limitPrice(limitPrice)
                                                            .originalAmount(originalAmount)
                                                            .id(orderHash)
                                                            .build();
                                                }),
                                returnOrderBookResponse
                                        .getBids()
                                        .stream()
                                        .map(
                                                bid -> {
                                                    BigDecimal limitPrice = Companion.safeParse(bid.getPrice());
                                                    BigDecimal originalAmount = Companion.safeParse(bid.getAmount());
                                                    String orderHash = bid.getOrderHash();
                                                    return new Builder(OrderType.ASK, currencyPair)
                                                            .limitPrice(limitPrice)
                                                            .originalAmount(originalAmount)
                                                            .id(orderHash)
                                                            .build();
                                                }));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        }

        @Override
        public Trades getTrades(CurrencyPair currencyPair, Object... args) {
            Trades ret = null;
            try {
                ret =   new Trades(
                                RestProxyFactory.createProxy(
                                        ReturnTradeHistoryApi.class, getDefaultExchangeSpecification().getSslUri())
                                        .tradeHistory(new TradeHistoryReq().market(Companion.cpairString(currencyPair)))
                                        .stream()
                                        .map(
                                                tradeHistoryItem -> new Trade.Builder()
                                                        .originalAmount(Companion.safeParse(tradeHistoryItem.getAmount()))
                                                        .price(Companion.safeParse(tradeHistoryItem.getPrice()))
                                                        .currencyPair(currencyPair)
                                                        .timestamp(new Date(tradeHistoryItem.getTimestamp().longValue()*1000 ))
                                                        .id((tradeHistoryItem.getTransactionHash()))
                                                        .type(tradeHistoryItem.getType() == IdexBuySell.BUY ? OrderType.BID : OrderType.ASK)
                                                        .build())
                                        .sorted(Comparator.comparing(Trade::getTimestamp))
                                        .collect(Collectors.toList()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        }
    }
}
