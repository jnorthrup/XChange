package org.knowm.xchange.idexjaxrs

import org.knowm.xchange.*
import org.knowm.xchange.currency.*
import org.knowm.xchange.dto.*


 fun main(args: Array<String>) {
    System.setProperty("XChangeDebug", "true")
    val apiKey = args[0]
    System.err.println("AccountInfo.... using arg[0] as account")
    val apiZekret = if (args.size > 1) args[1] else ""

    val idexjaxrs: IdexjaxrsExchange = ExchangeFactory.INSTANCE.createExchange(IdexjaxrsExchange::class.java, apiKey,
                                                                     apiZekret) as IdexjaxrsExchange



    val ticker = idexjaxrs.marketDataService.getTicker(CurrencyPair(
            "OMG",
            "ETH"
    ));
    val last = ticker.last
    val currencyPair = CurrencyPair("OMG/ETH")
    System.err.println("normalized order for 1 "+currencyPair+" at " + last + "ETH  ")

    val ethCur = currencyPair.counter
    val omgCur = currencyPair.base
    val normalizedLimitOrderReq = idexjaxrs.tradeService.createNormalizedLimitOrderReq(baseCurrency = omgCur,
                                                                                  counterCurrency = ethCur,
                                                                                  type = Order.OrderType.BID,
                                                                                  limitPrice = last,
                                                                                  originalAmount = last,
                                                                                  contractAddress = "0x2a0c0dbecc7e4d658f48e01e3fa353f44050c208",
                                                                                  nonce =  1000.toBigInteger() ,
                                                                                  expires = 100000.toBigInteger())
    System.err.println(normalizedLimitOrderReq)
    System.err.println("recv:"+normalizedLimitOrderReq.amountBuy.toBigDecimal()*"1e-${ (idexjaxrs.exchangeMetaData.currencies[omgCur] as IdexCurrencyMeta).decimals}".toBigDecimal())
    System.err.println("spend"+normalizedLimitOrderReq.amountSell.toBigDecimal()*"1e-${ (idexjaxrs.exchangeMetaData.currencies[ethCur ] as IdexCurrencyMeta).decimals}".toBigDecimal())
}