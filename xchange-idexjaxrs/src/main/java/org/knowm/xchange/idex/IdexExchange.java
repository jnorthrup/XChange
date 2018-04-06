package org.knowm.xchange.idex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder.Builder;
import org.knowm.xchange.idexjaxrs.dto.*;
import org.knowm.xchange.idexjaxrs.service.ReturnOrderBookApi;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.math.BigDecimal;
import java.util.Date;

public class IdexExchange extends BaseExchange {

    public static BigDecimal safeParse(String s) {
        BigDecimal ret = null;
        try {
            ret = new BigDecimal(s);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return ret;
    }

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
                return "https://api.idex.market/";
            }
        };
    }

    @Override
    public MarketDataService getMarketDataService() {
        return new MarketDataService() {
            @Override
            public Ticker getTicker(CurrencyPair currencyPair, Object... args) {

                ReturnTickerApi proxy =
                        RestProxyFactory.createProxy(ReturnTickerApi.class, exchangeSpecification.getSslUri());
                Ticker ret = null;

                Market market = new Market();
                String market1 = cpairString(currencyPair);
                Market market2 = market.market(market1);
                ReturnTickerResponse ticker = null;
                try {
                    ticker = proxy.ticker(market2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.err.println(ticker);
                ret = new Ticker.Builder()
                        .currencyPair(currencyPair)
                        .last(IdexExchange.safeParse(ticker.getLast()))
                        .ask(IdexExchange.safeParse(ticker.getLowestAsk()))
                        .bid(IdexExchange.safeParse(ticker.getHighestBid()))
                        .volume(IdexExchange.safeParse(ticker.getBaseVolume()))
                        .quoteVolume(IdexExchange.safeParse(ticker.getQuoteVolume()))
                        .high(IdexExchange.safeParse(ticker.getHigh()))
                        .low(IdexExchange.safeParse(ticker.getLow()))
                        .build();

                return ret;
            }

            @Override
            public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) {
                ReturnOrderBookApi proxy1 = RestProxyFactory.createProxy(ReturnOrderBookApi.class, getDefaultExchangeSpecification().getSslUri());
                OrderBook ret = null;
                try {
                    ReturnOrderBookResponse returnOrderBookResponse = proxy1.orderBook(new OrderBookReq().market(cpairString(currencyPair)));
                    ret = new OrderBook(new Date(),
                            returnOrderBookResponse.getAsks().stream().map(ask -> {
                                BigDecimal limitPrice = safeParse(ask.getPrice());
                                BigDecimal originalAmount = safeParse(ask.getAmount());
                                String orderHash = ask.getOrderHash();
                                return new Builder(OrderType.ASK, currencyPair)
                                        .limitPrice(limitPrice)
                                        .originalAmount(originalAmount)
                                        .id(orderHash)
                                        .build();
                            }),
                            returnOrderBookResponse.getBids().stream().map(bid -> {
                                BigDecimal limitPrice = safeParse(bid.getPrice());
                                BigDecimal originalAmount = safeParse(bid.getAmount());
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
                return null;
            }
        };
    }

    public String cpairString(CurrencyPair currencyPair) {
        return currencyPair.base.getSymbol() + "_" + currencyPair.counter.getSymbol();
    }
}
