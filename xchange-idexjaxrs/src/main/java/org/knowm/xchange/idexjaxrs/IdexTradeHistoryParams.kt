package org.knowm.xchange.idexjaxrs

import org.knowm.xchange.idexjaxrs.dto.*
import org.knowm.xchange.service.trade.params.*

class IdexTradeHistoryParams : TradeHistoryParams, DepositsWithdrawalsReq() {}