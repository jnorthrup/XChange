package org.knowm.xchange.idex;


import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.RateLimit;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.idexjaxrs.dto.NextNonceReq;
import org.knowm.xchange.idexjaxrs.dto.ReturnCurrenciesResponse;
import org.knowm.xchange.idexjaxrs.dto.ReturnNextNonceResponse;
import org.knowm.xchange.idexjaxrs.dto.ReturnTickerRequestedWithNull;
import org.knowm.xchange.idexjaxrs.service.ReturnNextNonceApi;
import org.knowm.xchange.service.marketdata.MarketDataService;
import si.mazi.rescu.RestProxyFactory;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static java.math.BigDecimal.ZERO;
import static java.util.Arrays.asList;
import static org.knowm.xchange.idex.IdexMarketDataService.Companion.allCurrenciesStatic;

public class IdexExchange extends BaseExchange implements Exchange {
    private ReturnCurrenciesResponse allCurrenciesStatic;
    CurrencyPairMetaData unavailableCPMeta = new CurrencyPairMetaData(ZERO, ZERO, ZERO, 0)
;
    @org.jetbrains.annotations.NotNull
    public final CurrencyPairMetaData getUnavailableCPMeta() {
        return this.unavailableCPMeta;
    }

    public final ExchangeMetaData getExchangeMetaData(){
        ReturnCurrenciesResponse allCurrenciesStatic = null;
        try {
            allCurrenciesStatic = allCurrenciesStatic();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedHashMap<CurrencyPair, CurrencyPairMetaData> currencyPairs = new LinkedHashMap<>();;
        ReturnTickerRequestedWithNull allTickers = IdexMarketDataService.Companion.allTickers;

        allTickers.keySet().forEach(s -> currencyPairs.put(Companion.getCurrencyPair(s), unavailableCPMeta));
        LinkedHashMap<Currency, CurrencyMetaData>  linkedHashMap  = new LinkedHashMap<>();
        allCurrenciesStatic.forEach((key, value) -> linkedHashMap.put(Currency.getInstance(key),
                new Companion.IdexCurrencyMeta(0,
                        BigDecimal.ZERO,
                        value.getAddress(),
                        value.getName(),
                        value.getDecimals())));
        RateLimit[] publicRateLimits = {};
        return new ExchangeMetaData(currencyPairs,  linkedHashMap,publicRateLimits, publicRateLimits, Boolean.FALSE);

    }
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
                ReturnNextNonceResponse var10000 = getNextNonceApi().nextNonce(new NextNonceReq().address(this.getExchangeSpecification().getApiKey()));
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

        public static void main(String... args) throws IOException {
            Exchange exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange.class);
            CurrencyPair currencyPair = getCurrencyPair("ETH_OMG");
            MarketDataService marketDataService = exchange.getMarketDataService();
            {
                Ticker ticker;
                ticker = marketDataService.getTicker(currencyPair);
                System.out.println(ticker);
            }
            Trades trades = marketDataService.getTrades(currencyPair);
            System.err.println(Arrays.deepToString(new Object[]{trades}));
            if (args.length > 0) exchange.getExchangeSpecification().setApiKey(args[0]);
            if (args.length > 1) exchange.getExchangeSpecification().setSecretKey(args[1]);
            AccountInfo accountInfo = exchange.getAccountService().getAccountInfo();
            System.err.println(accountInfo);
            CurrencyPair kin = getCurrencyPair("ETH_KIN");
            Ticker ticker = exchange.getMarketDataService().getTicker(kin);
            System.err.println(ticker);
            String placeLimitOrder = exchange.getTradeService().placeLimitOrder(new LimitOrder(Order.OrderType.ASK, BigDecimal.ONE, kin, null, null, new BigDecimal(42L)));
            System.err.println((placeLimitOrder));

        }
/*

        fun main(args:Array<String>) {
            System.setProperty("XChangeDebug", "true")
            val apiKey = args[0]
            System.err.println("AccountInfo.... using arg[0] as account")
            val apiZekret = if (args.size > 1) args[1] else ""

            val idex: Exchange = ExchangeFactory.INSTANCE.createExchange(IdexExchange::class.java, apiKey,
                    apiZekret) as IdexExchange;
            val accountInfo = idex.accountService.accountInfo

            System.err.println(accountInfo.toString())
            System.err.println("Ticker... looking for KIN (ticker for ETH_KIN)")
            val kin = CurrencyPair("KIN", "ETH")
            val ticker = idex.marketDataService.getTicker(kin);
            val tickers = ticker.toString()
            System.err.println(
                    tickers
            )
            val placeLimitOrder = idex.tradeService.placeLimitOrder(
                    LimitOrder(Order.OrderType.ASK, 1 .toBigDecimal(), kin, null, null,
                            42 .toBigDecimal()))
            System.err.println(gson.toJson(placeLimitOrder))
        }
*/


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

        public static final class IdexCurrencyMeta extends CurrencyMetaData {
            @NotNull
            private final String address;
            @NotNull
            private final String name;
            @NotNull
            private final BigInteger decimals;

            public IdexCurrencyMeta(int scale, @Nullable BigDecimal withdrawalFee, @NotNull String address, @NotNull String name, @NotNull BigInteger decimals) {
//                Intrinsics.checkParameterIsNotNull(address, "address");
//                Intrinsics.checkParameterIsNotNull(name, "name");
//                Intrinsics.checkParameterIsNotNull(decimals, "decimals");
                super(scale, withdrawalFee);
                this.address = address;
                this.name = name;
                this.decimals = decimals;
            }

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
        }
    }

}
