package org.knowm.xchange.idexjaxrs

import org.knowm.xchange.*
import org.knowm.xchange.currency.*

class IdexSmokeTest {}

fun main (args: Array<String>) {
    System.setProperty("XChangeDebug", "true")
    val apiKey = args[0]
    System.err.println("AccountInfo.... using arg[0] as account")
    val apiZekret = if (args.size > 1) args[1] else ""

    val idexjaxrs: IdexjaxrsExchange = ExchangeFactory.INSTANCE.createExchange(IdexjaxrsExchange::class.java, apiKey,
                                                                     apiZekret) as IdexjaxrsExchange;
    val accountInfo = idexjaxrs.accountService.accountInfo

    System.err.println(accountInfo.toString())

    System.err.println("Ticker... looking for OMG (ticker for ETH_OMG)")
    val omgPair = CurrencyPair("OMG", "ETH")
    val ticker = idexjaxrs.marketDataService.getTicker(omgPair);
    val tickers = ticker.toString()
    System.err.println(
            tickers
    )
    System.err.println("Trade History... using arg[2]")

    val split = args[2].split("_")
    val currencyPair = CurrencyPair(split[1], split[0])
    System.err.println(idexjaxrs.marketDataService.getTrades(currencyPair)).toString()

    System.err.println("Orderbook... using arg[2]")
    System.err.println(idexjaxrs.marketDataService.getOrderBook(currencyPair)).toString()


    System.err.println("Funding History... using arg[0]")
    val c: IdexTradeHistoryParams = idexjaxrs.accountService.createFundingHistoryParams() as IdexTradeHistoryParams
    c.address(apiKey)
    System.err.println(idexjaxrs.accountService.getFundingHistory(c))

    System.err.println("All currencies (lazy)")
    var t = System.currentTimeMillis()
    val allCurrencies = IdexMarketDataService.allBase
    System.err.println("fetched/sorted in " + (System.currentTimeMillis() - t) + "(ms)")

    System.err.println(allCurrencies)

    System.err.println("All counter (lazy)")
    t = System.currentTimeMillis()
    val allInstrument = IdexMarketDataService.allCounter
    System.err.println("sorted in ${System.currentTimeMillis() - t}(ms)")
    System.err.println(allInstrument)
    val currencies = IdexMarketDataService(idexjaxrs).currencies()
    System.err.println(currencies)
}