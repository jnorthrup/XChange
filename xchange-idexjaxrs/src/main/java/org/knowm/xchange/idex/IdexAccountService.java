package org.knowm.xchange.idex;


import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.FundingRecord.Status;
import org.knowm.xchange.dto.account.FundingRecord.Type;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.idexjaxrs.dto.*;
import org.knowm.xchange.idexjaxrs.service.ReturnCompleteBalancesApi;
import org.knowm.xchange.idexjaxrs.service.ReturnDepositsWithdrawalsApi;
import org.knowm.xchange.idexjaxrs.service.WithdrawApi;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;
import si.mazi.rescu.RestProxyFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.knowm.xchange.idex.IdexExchange.Companion.safeParse;


public class IdexAccountService implements AccountService {
    private final String apiKey;
    private final IdexExchange idexExchange;

    public IdexAccountService(@NotNull IdexExchange idexExchange) {
        Intrinsics.checkParameterIsNotNull(idexExchange, "idexExchange");
        this.idexExchange = idexExchange;
        apiKey = this.idexExchange.getExchangeSpecification().getApiKey();
    }



    public final String getApiKey() {
        return apiKey;
    }

    @NotNull
    public AccountInfo getAccountInfo() {
        AccountInfo ret = null;
        try {
            String apiKey = idexExchange.getExchangeSpecification().getApiKey();
            String s = apiKey.substring(0, 6) + "…";
            ReturnCompleteBalancesApi proxy = RestProxyFactory.createProxy(ReturnCompleteBalancesApi.class, idexExchange.getExchangeSpecification().getSslUri());
            ReturnCompleteBalancesResponse returnBalancesPost = null;
            ret = null;
            returnBalancesPost = proxy.completeBalances(new CompleteBalancesReq().address(apiKey));

            ret = new AccountInfo(new Wallet(s, returnBalancesPost.entrySet().stream().map(entry -> {
                Balance balance = new Balance(new Currency(entry.getKey()), null, entry.getValue().getAvailable(), entry.getValue().getOnOrders());
                return balance;
            }).collect(Collectors.toList())));


        } catch (Exception e) {


        }
        return ret;
    }


    @NotNull
    public String requestDepositAddress(@Nullable org.knowm.xchange.currency.Currency currency, @NotNull String... args) {
        return idexExchange.getExchangeSpecification().getApiKey();
    }

    @Override
    @NotNull
    public List<FundingRecord> getFundingHistory(@NotNull TradeHistoryParams params) {
        List<FundingRecord> ret = null;
        if (!(params instanceof IdexTradeHistoryParams)) {
            throw new Error("getFundingHistory requires " + IdexTradeHistoryParams.class.getCanonicalName());
        } else {
            try {
                ret= mutableList(RestProxyFactory.createProxy(ReturnDepositsWithdrawalsApi.class, idexExchange.getDefaultExchangeSpecification().getSslUri()).fundingHistory((DepositsWithdrawalsReq) params));
            } catch (Exception e) {


            }
        }
        return ret;
    }

    private final List<FundingRecord> mutableList(ReturnDepositsWithdrawalsResponse returnDepositsWithdrawalsPost) {
        return (List<FundingRecord>) Arrays.asList(returnDepositsWithdrawalsPost.getWithdrawals().stream().map(
                fundingLedger -> new FundingRecord(
                        apiKey,
                        new Date(Long.parseLong(fundingLedger.getTimestamp()) * 1000),
                        new Currency(fundingLedger.getCurrency()),
                        safeParse(fundingLedger.getAmount()),
                        fundingLedger.getTransactionHash(),
                        fundingLedger.getDepositNumber(),
                        Type.WITHDRAWAL,
                        Status.resolveStatus(fundingLedger.getStatus()),
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        ""
                )).collect(Collectors.toList()),
                returnDepositsWithdrawalsPost.getDeposits().stream().map(
                        fundingLedger1 -> new FundingRecord(
                                apiKey,
                                new Date(Long.parseLong(fundingLedger1.getTimestamp()) * 1000),
                                new Currency(fundingLedger1.getCurrency()),
                                safeParse(fundingLedger1.getAmount()),
                                fundingLedger1.getTransactionHash(),
                                fundingLedger1.getDepositNumber(),
                                Type.WITHDRAWAL,
                                Status.resolveStatus(fundingLedger1.getStatus()),
                                BigDecimal.ZERO,
                                BigDecimal.ZERO,
                                ""
                        )).collect(Collectors.toList()))
                        .stream().flatMap(List::stream)
                        .sorted(Comparator.comparing(FundingRecord::getDate));
    }


    @NotNull
    public TradeHistoryParams createFundingHistoryParams() {
        return new IdexTradeHistoryParams();
    }

    @NotNull
    public String withdrawFunds(@NotNull WithdrawFundsParams w) {
         String ret="error";
        if (w instanceof IdexWithdraw) {
            try {
                WithdrawResponse withdraw = RestProxyFactory.createProxy(WithdrawApi.class, idexExchange.getDefaultExchangeSpecification().getSslUri()).withdraw((WithdrawReq) w);
                ret = withdraw.toString() ;
            } catch (Exception e) {


            }

        } else {
            throw new Error("withdraw method requires " + org.knowm.xchange.idex.IdexWithdraw.class.getCanonicalName());
        }
        return ret;
    }

    @Override
    @NotNull
    public String withdrawFunds(@NotNull Currency currency, @NotNull BigDecimal amount, @NotNull String address) {
//todo: validate the RSV from the WithdrawlReq created
        //        Intrinsics.checkParameterIsNotNull(currency, "currency");
//        Intrinsics.checkParameterIsNotNull(amount, "amount");
//        Intrinsics.checkParameterIsNotNull(address, "address");
//        new IdexWithdraw;
//        String var10003 = this.apiKey;
//        Intrinsics.checkExpressionValueIsNotNull(this.apiKey, "apiKey");
//        Intrinsics.checkExpressionValueIsNotNull(amount.toString(), "amount.toString()");
//        Intrinsics.checkExpressionValueIsNotNull(currency.getSymbol(), "currency!!.symbol");
//        Intrinsics.checkExpressionValueIsNotNull(this.idexExchange.getTradeService().getIdexServerNonce(), "idexExchange.tradeService.idexServerNonce");
//        String var4 = "provide threadlocals? for S";
        throw  (new NotImplementedError("An operation is not implemented: withdraw signature verification " /*+ var4*/));
    }


}

