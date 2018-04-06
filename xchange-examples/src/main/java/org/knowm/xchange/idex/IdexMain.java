package org.knowm.xchange.idex;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class IdexMain {
  public static void main(String[] args) throws IOException {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
    CurrencyPair currencyPair = new CurrencyPair("ETH", "OMG");
    MarketDataService marketDataService = exchange.getMarketDataService();
    Ticker ticker = marketDataService.getTicker(currencyPair);
    System.out.println(ticker);
  }
}
