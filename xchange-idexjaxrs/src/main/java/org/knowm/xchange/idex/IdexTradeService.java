package org.knowm.xchange.idex;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.*;
import org.knowm.xchange.idexjaxrs.service.*;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import si.mazi.rescu.RestProxyFactory;

import java.io.IOException;
import java.util.Collection;

public class IdexTradeService implements TradeService {
    private final IdexExchange idexExchange;

    @Override
    public OpenOrders getOpenOrders() {
        ReturnOpenOrdersApi proxy =RestProxyFactory.createProxy(ReturnOpenOrdersApi.class,idexExchange.getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) {
        ReturnOpenOrdersApi proxy =RestProxyFactory.createProxy(ReturnOpenOrdersApi.class,idexExchange.getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public String placeLimitOrder(LimitOrder limitOrder) {
        OrderApi proxy =RestProxyFactory.createProxy(OrderApi.class,idexExchange.getExchangeSpecification().getSslUri());
        return null;
    }

    @Override
    public boolean cancelOrder(String s) {
        CancelApi proxy =RestProxyFactory.createProxy(CancelApi.class,idexExchange.getExchangeSpecification().getSslUri());
        return false;
    }

    @Override
    public boolean cancelOrder(CancelOrderParams cancelOrderParams) {
        CancelApi proxy =RestProxyFactory.createProxy(CancelApi.class,idexExchange.getExchangeSpecification().getSslUri());
    }

    @Override
    public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) {
        ReturnTradeHistoryApi proxy =RestProxyFactory.createProxy(ReturnTradeHistoryApi.class,idexExchange.getExchangeSpecification().getSslUri());
    }

    @Override
    public Collection<Order> getOrder(String... strings) {
        ReturnOpenOrdersApi proxy =RestProxyFactory.createProxy(ReturnOpenOrdersApi.class,idexExchange.getExchangeSpecification().getSslUri());
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

    public IdexTradeService(IdexExchange idexExchange) {

        this.idexExchange = idexExchange;
    }
}
