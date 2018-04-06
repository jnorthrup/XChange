package org.knowm.xchange.idex;

import java.io.IOException;
import java.math.BigDecimal;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Ticker.Builder;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.idexjaxrs.dto.Market;
import org.knowm.xchange.idexjaxrs.dto.ReturnTickerResponse;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class IdexExchange extends BaseExchange {

  @Override
  protected void initServices() {}

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
        try {
          Market market = new Market();
          ReturnTickerResponse ticker =
              proxy.ticker(
                  market.market(
                      currencyPair.counter.getSymbol() + "_" + currencyPair.base.getSymbol()));

          System.err.println(ticker.toString());
          ret =
              new Builder()
                  .currencyPair(currencyPair)
                  .last(new BigDecimal(ticker.getLast()))
                  .ask(new BigDecimal(ticker.getLowestAsk()))
                  .bid(new BigDecimal(ticker.getHighestBid()))
                  .volume(new BigDecimal(ticker.getBaseVolume()))
                  .high(new BigDecimal(ticker.getHigh()))
                  .low(new BigDecimal(ticker.getLow()))
                  .build() /*incomplete*/;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return ret;
      }

      @Override
      public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) {
        return null;
      }

      @Override
      public Trades getTrades(CurrencyPair currencyPair, Object... args) {
        return null;
      }
    };
  }

}
