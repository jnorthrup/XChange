package org.knowm.xchange.idex;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.idexjaxrs.dto.NextNonceReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnNextNonceResponse;
import org.knowm.xchange.idexjaxrs.service.ReturnNextNonceApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;

import static java.util.Arrays.asList;

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
            }
            return ret;
        }

        public static String getMarket(CurrencyPair currencyPair) {
            return currencyPair.base.getSymbol() + "_" + currencyPair.counter.getSymbol();
        }
        public static CurrencyPair getCurrencyPair(String market) {
            CurrencyPair currencyPair;
            Iterator<String> syms = asList(market.split("_")).iterator();
            currencyPair = new CurrencyPair(syms.next(), syms.next());
            return currencyPair;
        }
        public static void main(String[] args) throws IOException {
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
            CurrencyPair currencyPair = getCurrencyPair("ETH_OMG");
            MarketDataService marketDataService = exchange.getMarketDataService();
            Ticker ticker = marketDataService.getTicker(currencyPair);
            System.out.println(ticker);
            Trades trades = marketDataService.getTrades(currencyPair);
            System.err.println(Arrays.deepToString(new Object[]{trades}));
        }



        public static class IdexExchangeSpecification extends ExchangeSpecification {
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

        public final class IdexCurrencyMeta extends CurrencyMetaData {
            @NotNull
            private final String address;
            @NotNull
            private final String name;
            @NotNull
            private final BigInteger decimals;

            @NotNull
            public final String getAddress() {
                return this.address;
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final BigInteger getDecimals() {
                return this.decimals;
            }

            public IdexCurrencyMeta(int scale, @Nullable BigDecimal withdrawalFee, @NotNull String address, @NotNull String name, @NotNull BigInteger decimals) {
//                Intrinsics.checkParameterIsNotNull(address, "address");
//                Intrinsics.checkParameterIsNotNull(name, "name");
//                Intrinsics.checkParameterIsNotNull(decimals, "decimals");
                super(scale, withdrawalFee);
                this.address = address;
                this.name = name;
                this.decimals = decimals;
            }
        }

    }

}
