package org.knowm.xchange.idex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.idexjaxrs.dto.Market;
import org.knowm.xchange.idexjaxrs.dto.ReturnTickerResponse;
import org.knowm.xchange.idexjaxrs.service.ReturnTickerApi;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.knowm.xchange.service.trade.TradeService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class IdexExchange extends BaseExchange implements Exchange {

    @Override
    public ExchangeSpecification getExchangeSpecification() {
        return new ExchangeSpecification(IdexExchange.class) {
            @Override
            public String getSslUri() {
                return "https://api.idex.market";
            }
        };
    }

    @Override
    public MarketDataService getMarketDataService() {
        return new MarketDataService() {
            @Override
            public Ticker getTicker(CurrencyPair currencyPair, Object... args) throws IOException {
                String sslUri = IdexExchange.this.exchangeSpecification.getSslUri();
                ReturnTickerApi proxy = RestProxyFactory.createProxy(ReturnTickerApi.class, sslUri);
                Market market = new Market().market(currencyPair.counter.getSymbol() + "_" + currencyPair.base.getSymbol());
                ReturnTickerResponse ticker = (ReturnTickerResponse) proxy.ticker(market).getEntity();

                return (Ticker) new Ticker.Builder()
                        .currencyPair(currencyPair)
                        .timestamp(new Date())
                        .open(new BigDecimal(ticker.getLast()))
                        .ask(new BigDecimal(ticker.getLowestAsk()))
                        .bid(new BigDecimal(ticker.getHighestBid()))
                        .last(new BigDecimal(ticker.getLast()))
                        .high(new BigDecimal(ticker.getHigh()))
                        .low(new BigDecimal(ticker.getLow()))
                        .volume(new BigDecimal(ticker.getBaseVolume()))
                        .quoteVolume(new BigDecimal(ticker.getQuoteVolume()))
                        .build();

            }

            @Override
            public OrderBook getOrderBook(CurrencyPair currencyPair, Object... args) throws IOException {
                return null;
            }

            @Override
            public Trades getTrades(CurrencyPair currencyPair, Object... args) throws IOException {
                return null;
            }
        }
    }

    @Override
    public TradeService getTradeService() {
        return super.getTradeService();
    }

    @Override
    public AccountService getAccountService() {
        return super.getAccountService();
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
        return null;
    }
}
