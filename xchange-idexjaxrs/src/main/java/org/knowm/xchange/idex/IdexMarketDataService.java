package org.knowm.xchange.idex;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.idexjaxrs.dto.*;
import org.knowm.xchange.idexjaxrs.service.ReturnOrderBookApi;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.idexjaxrs.service.ReturnTradeHistoryApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;


public class IdexMarketDataService implements MarketDataService {

    IdexExchange idexExchange;

    public IdexMarketDataService(IdexExchange idexExchange) {
        this.idexExchange = idexExchange;
    }

    @Override
    public Ticker getTicker(CurrencyPair currencyPair, Object... args) {

        ReturnTickerApi proxy =
                RestProxyFactory.createProxy(ReturnTickerApi.class, idexExchange.getExchangeSpecification().getSslUri());
        Ticker ret = null;

        Market market = new Market();
        String market1 = IdexExchange.Companion.cpairString(currencyPair);
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
                        .last(IdexExchange.Companion.safeParse(ticker.getLast()))
                        .ask(IdexExchange.Companion.safeParse(ticker.getLowestAsk()))
                        .bid(IdexExchange.Companion.safeParse(ticker.getHighestBid()))
                        .volume(IdexExchange.Companion.safeParse(ticker.getBaseVolume()))
                        .quoteVolume(IdexExchange.Companion.safeParse(ticker.getQuoteVolume()))
                        .high(IdexExchange.Companion.safeParse(ticker.getHigh()))
                        .low(IdexExchange.Companion.safeParse(ticker.getLow()))
                        .build();

        return ret;
    }

    @Override
    public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) {
        ReturnOrderBookApi proxy1 =
                RestProxyFactory.createProxy(
                        ReturnOrderBookApi.class, idexExchange.getDefaultExchangeSpecification().getSslUri());
        OrderBook ret = null;
        try {
            ReturnOrderBookResponse returnOrderBookResponse =
                    proxy1.orderBook(new OrderBookReq().market(IdexExchange.Companion.cpairString(currencyPair)));
            ret =
                    new OrderBook(
                            new Date(),
                            returnOrderBookResponse
                                    .getAsks()
                                    .stream()
                                    .map(
                                            ask -> {
                                                BigDecimal limitPrice = IdexExchange.Companion.safeParse(ask.getPrice());
                                                BigDecimal originalAmount = IdexExchange.Companion.safeParse(ask.getAmount());
                                                String orderHash = ask.getOrderHash();
                                                return new LimitOrder.Builder(Order.OrderType.ASK, currencyPair)
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
                                                BigDecimal limitPrice = IdexExchange.Companion.safeParse(bid.getPrice());
                                                BigDecimal originalAmount = IdexExchange.Companion.safeParse(bid.getAmount());
                                                String orderHash = bid.getOrderHash();
                                                return new LimitOrder.Builder(Order.OrderType.ASK, currencyPair)
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
                                    ReturnTradeHistoryApi.class, idexExchange.getDefaultExchangeSpecification().getSslUri())
                                    .tradeHistory(new TradeHistoryReq().market(IdexExchange.Companion.cpairString(currencyPair)))
                                    .stream()
                                    .map(
                                            tradeHistoryItem -> new Trade.Builder()
                                                    .originalAmount(IdexExchange.Companion.safeParse(tradeHistoryItem.getAmount()))
                                                    .price(IdexExchange.Companion.safeParse(tradeHistoryItem.getPrice()))
                                                    .currencyPair(currencyPair)
                                                    .timestamp(new Date(tradeHistoryItem.getTimestamp().longValue()*1000 ))
                                                    .id((tradeHistoryItem.getTransactionHash()))
                                                    .type(tradeHistoryItem.getType() == IdexBuySell.BUY ? Order.OrderType.BID : Order.OrderType.ASK)
                                                    .build())
                                    .sorted(Comparator.comparing(Trade::getTimestamp))
                                    .collect(Collectors.toList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}
