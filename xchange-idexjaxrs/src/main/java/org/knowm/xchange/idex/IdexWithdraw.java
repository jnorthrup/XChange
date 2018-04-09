package org.knowm.xchange.idex;

import org.jetbrains.annotations.NotNull;
import org.knowm.xchange.idexjaxrs.dto.WithdrawReq;
import org.knowm.xchange.service.trade.params.WithdrawFundsParams;

import java.math.BigInteger;

final class IdexWithdraw extends WithdrawReq implements WithdrawFundsParams {
    public IdexWithdraw(@NotNull String address, @NotNull String amount, @NotNull String token, @NotNull BigInteger nonce, @NotNull String s, @NotNull BigInteger v, @NotNull String r) {
       this.address(address).amount(amount).token(token).nonce(nonce).s(s).v(v).r(r);
    }
}
