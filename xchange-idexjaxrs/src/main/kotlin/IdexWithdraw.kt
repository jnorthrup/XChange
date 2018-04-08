package org.knowm.xchange.idexjaxrs


import org.knowm.xchange.idexjaxrs.dto.*
import org.knowm.xchange.service.trade.params.*
import java.math.*

class IdexWithdraw(address: String, amount: String, token: String, nonce: BigInteger, s: String, v: BigInteger,
                   r: String) : WithdrawReq(), WithdrawFundsParams {init {
    address(address)
            .amount(amount)
            .token(token)
            .nonce(nonce)
            .s(s)
            .v(v)
            .r(r)
}
}

