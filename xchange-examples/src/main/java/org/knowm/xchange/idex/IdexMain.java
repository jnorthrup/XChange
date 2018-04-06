package org.knowm.xchange.idex;

import java.io.IOException;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;

public class IdexMain {
  public static void main(String[] args) throws IOException {
    Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
    Ticker ticker = exchange.getMarketDataService().getTicker(new CurrencyPair("OMG", "ETH"));
    System.out.println(ticker);
  }
}
