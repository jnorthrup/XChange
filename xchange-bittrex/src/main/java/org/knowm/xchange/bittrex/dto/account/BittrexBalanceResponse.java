package org.knowm.xchange.bittrex.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BittrexBalanceResponse {

  @JsonProperty("success")
  private Boolean success;
  @JsonProperty("message")
  private String message;
  @JsonProperty("result")
  private BittrexBalance result;

  @JsonProperty("success")
  public Boolean getSuccess() {

    return success;
  }

  @JsonProperty("success")
  public void setSuccess(Boolean success) {

    this.success = success;
  }

  @JsonProperty("message")
  public String getMessage() {

    return message;
  }

  @JsonProperty("message")
  public void setMessage(String message) {

    this.message = message;
  }

  @JsonProperty("result")
  public BittrexBalance getResult() {

    return result;
  }

  @JsonProperty("result")
  public void setResult(BittrexBalance result) {

    this.result = result;
  }

  @Override
  public String toString() {

    return "BittrexBalancesResponse [message=" + message + ", result=" + result + ", success=" + success + "]";
  }

}
