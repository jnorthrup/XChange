package org.knowm.xchange.idex;

import java.io.IOException;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.idexjaxrs.dto.Market;
import org.knowm.xchange.idexjaxrs.dto.ReturnTickerResponse;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

public class IdexExchange extends BaseExchange implements Exchange {

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
      public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {

        ReturnTickerApi proxy = RestProxyFactory.createProxy(ReturnTickerApi.class, IdexExchange.this.exchangeSpecification.getSslUri());
        Ticker ret = null;
        try {
          Market market = new Market();
          ReturnTickerResponse ticker = proxy.ticker(market.market(currencyPair.counter.getSymbol() + "_" + currencyPair.base.getSymbol()));
          ret = new Ticker.Builder().currencyPair(currencyPair).build()/*incomplete*/;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return ret;
      }


      @Override
      public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
        return null;
      }

      @Override
      public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
        return null;
      }
    };
  }

  public static void main(String[] args) throws IOException {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
    Ticker ticker = exchange.getMarketDataService().getTicker(new CurrencyPair(
            "OMG",
            "ETH"
    ));
    System.out.println(ticker);

  }
}