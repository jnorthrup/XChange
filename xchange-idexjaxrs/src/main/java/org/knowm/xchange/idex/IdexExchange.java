package org.knowm.xchange.idex;

import java.math.BigDecimal;
import org.knowm.xchange.BaseExchange;
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
          String market1 = currencyPair.base.getSymbol() + "_" + currencyPair.counter.getSymbol();
          Market market2 = market.market(market1);
          ReturnTickerResponse ticker = proxy.ticker(market2);

          System.err.println(ticker.toString());
          BigDecimal last = safeParse(ticker.getLast());
          BigDecimal ask = safeParse(ticker.getLowestAsk());
          BigDecimal bid = safeParse(ticker.getHighestBid());
          BigDecimal volume = safeParse(ticker.getBaseVolume());
          BigDecimal high = safeParse(ticker.getHigh());
          BigDecimal low = safeParse(ticker.getLow());
          ret =
              new Builder()
                  .currencyPair(currencyPair)
                  .last(last)
                  .ask(ask)
                  .bid(bid)
                  .volume(volume)
                  .high(high)
                  .low(low)
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

  public static BigDecimal safeParse(String s) {
    try {
      return new BigDecimal(s);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
