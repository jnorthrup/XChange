package org.knowm.xchange.idex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.idexjaxrs.dto.NextNonceReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnNextNonceResponse;
import org.knowm.xchange.idexjaxrs.service.ReturnNextNonceApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

public class IdexExchange extends BaseExchange implements Exchange {

    private IdexAccountService idexAccountService;
    private IdexTradeService idexTradeService;
    private IdexMarketDataService idexMarketDataService;
    private ReturnNextNonceApi nextNonceApi;

    public IdexExchange() {
    }

    public ReturnNextNonceApi getNextNonceApi() {
        if (null == nextNonceApi)
            nextNonceApi = RestProxyFactory.createProxy(ReturnNextNonceApi.class, exchangeSpecification.getSslUri());
        return nextNonceApi;
    }

    public IdexAccountService getAccountService() {
        if (null == idexAccountService) idexAccountService = new IdexAccountService(this);
        return idexAccountService;
    }

    public IdexMarketDataService getMarketDataService() {
        if (null == idexMarketDataService) idexMarketDataService = new IdexMarketDataService(this);
        return idexMarketDataService;
    }

    public IdexTradeService getTradeService() {
        if (null == idexTradeService) idexTradeService = new IdexTradeService(this);
        return idexTradeService;
    }

    @Override
    protected void initServices() {
    }

    @Override
    public SynchronizedValueFactory<Long> getNonceFactory() {
        return (SynchronizedValueFactory) () -> {
            Long ret = null;
            try {
                ReturnNextNonceResponse var10000 = getNextNonceApi().nextNonce(new NextNonceReq());
                ret = var10000.getNonce().longValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ret;
        };
    }

    @Override
    public ExchangeSpecification getDefaultExchangeSpecification() {
        return new Companion.IdexExchangeSpecification();
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
