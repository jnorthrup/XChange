package org.knowm.xchange.idex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class IdexExchange extends BaseExchange implements Exchange {

    @Override
    public AccountService getAccountService() {
        return new IdexAccountService(this);
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
        return new Companion.IdexExchangeSpecification();
    }

    @Override
    public MarketDataService getMarketDataService() {
        return new IdexMarketDataService(this);
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
        static class IdexExchangeSpecification extends ExchangeSpecification {
            public IdexExchangeSpecification() {
                super(IdexExchange.class);
            }

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
        }
    }

}
