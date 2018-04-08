package org.knowm.xchange.idexjaxrs

import org.knowm.xchange.currency.Currency
import org.knowm.xchange.dto.account.*
import org.knowm.xchange.idexjaxrs.dto.*
import org.knowm.xchange.idexjaxrs.service.*

import org.knowm.xchange.service.account.*
import org.knowm.xchange.service.trade.params.*
import java.math.*
import java.util.*

class IdexAccountService(private val idexjaxrsExchange: IdexjaxrsExchange) : AccountApi(),
        AccountService {
    val apiKey = idexjaxrsExchange.exchangeSpecification.apiKey

    init {

        if (IdexjaxrsExchange.debugMe) {
//            apiClient = ApiClient()
//            IdexjaxrsExchange.setupDebug(apiClient)
        }
    }

    override fun getAccountInfo(): AccountInfo {
        val apiKey = idexjaxrsExchange.exchangeSpecification.apiKey
        val s = apiKey.slice(0.rangeTo(6)) + "…"
        val returnBalancesPost = completeBalances(CompleteBalancesReq().apply { address = apiKey })

        val entries = returnBalancesPost.entries
        val toList = entries.toList()
        val list = toList.map {
            val key = it.key!!
            val value = it.value!!
            val currency = Currency(key)
            Balance(currency, null, value.available, value.onOrders)
        }
        val accountInfo = AccountInfo(
                Wallet(s, list))
        return accountInfo
    }

    override fun requestDepositAddress(currency: Currency?, vararg args: String?): String =//the safe option
            idexjaxrsExchange.exchangeSpecification.apiKey


    override fun getFundingHistory(params: TradeHistoryParams): MutableList<FundingRecord> {
        when {
            params is IdexTradeHistoryParams -> {
                val returnDepositsWithdrawalsPost = fundingHistory(params as DepositsWithdrawalsReq)
                return listOf(
                        returnDepositsWithdrawalsPost.deposits.map {
                            FundingRecord(
                                    apiKey,
                                    Date(it.timestamp.toLong() * 1000),
                                    Currency(it.currency),
                                    it.amount.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                                    it.transactionHash,
                                    it.depositNumber,
                                    FundingRecord.Type.DEPOSIT,
                                    FundingRecord.Status.resolveStatus(it.status),
                                    BigDecimal.ZERO,
                                    BigDecimal.ZERO,
                                    ""
                            )
                        },
                        returnDepositsWithdrawalsPost.withdrawals.map {
                            FundingRecord(
                                    apiKey,
                                    Date(it.timestamp.toLong() * 1000),
                                    Currency(it.currency),
                                    it.amount.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                                    it.transactionHash,
                                    it.depositNumber,
                                    FundingRecord.Type.WITHDRAWAL,
                                    FundingRecord.Status.resolveStatus(it.status),
                                    BigDecimal.ZERO,
                                    BigDecimal.ZERO,
                                    ""
                            )
                        }
                ).flatten().sortedBy { it.date }.toMutableList()
            }

            else -> throw  ApiException(
                    "getFundingHistory requires " + IdexTradeHistoryParams::class.java.canonicalName)
        }
    }

    override fun createFundingHistoryParams(): TradeHistoryParams {
        return IdexTradeHistoryParams()
    }

    /**
     * from the docs: "Useful response upon withdrawal success is in the works, for now simply test that there is no error property in the result object to confirm your withdrawal has succeeded."
     */
    override fun withdrawFunds(w: WithdrawFundsParams): String {
        when {
            w is IdexWithdraw -> {
                val withdraw = withdraw(w)
                return IdexjaxrsExchange.gson.toJson(withdraw)

            }
            else -> throw ApiException(
                    "withdraw method requires " + IdexWithdraw::class.java.canonicalName)
        }
    }


    override fun withdrawFunds(currency: Currency, amount: BigDecimal, address: String): String = withdrawFunds(
            IdexWithdraw(apiKey,
                         amount.toString(),
                         currency!!.symbol,
                         idexjaxrsExchange.tradeService.idexServerNonce,
                         s = TODO("provide threadlocals? for S"),
                         v = TODO("provide threadlocals? for V"),
                         r = TODO("provide threadlocals? for R"))
    )
}