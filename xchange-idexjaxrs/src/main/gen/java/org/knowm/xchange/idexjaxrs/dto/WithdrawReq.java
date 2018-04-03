package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class WithdrawReq   {
  
  private @Valid String address = null;
  private @Valid String amount = null;
  private @Valid String token = null;
  private @Valid java.math.BigInteger nonce = null;
  private @Valid String s = null;
  private @Valid java.math.BigInteger v = null;
  private @Valid String r = null;

  /**
   * (address string) - The address you are transacting from
   **/
  public WithdrawReq address(String address) {
    this.address = address;
    return this;
  }

  
  @ApiModelProperty(value = "(address string) - The address you are transacting from")
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * (uint256) - The raw amount you are withdrawing, not adjusted for token precision
   **/
  public WithdrawReq amount(String amount) {
    this.amount = amount;
    return this;
  }

  
  @ApiModelProperty(value = "(uint256) - The raw amount you are withdrawing, not adjusted for token precision")
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
  }

  /**
   * (address string) - The address of the token you are withdrawing from, see earlier notes for ETH
   **/
  public WithdrawReq token(String token) {
    this.token = token;
    return this;
  }

  
  @ApiModelProperty(value = "(address string) - The address of the token you are withdrawing from, see earlier notes for ETH")
  @JsonProperty("token")
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * (uint256) - One time numeric value associated with your address
   **/
  public WithdrawReq nonce(java.math.BigInteger nonce) {
    this.nonce = nonce;
    return this;
  }

  
  @ApiModelProperty(value = "(uint256) - One time numeric value associated with your address")
  @JsonProperty("nonce")
  public java.math.BigInteger getNonce() {
    return nonce;
  }
  public void setNonce(java.math.BigInteger nonce) {
    this.nonce = nonce;
  }

  /**
   **/
  public WithdrawReq s(String s) {
    this.s = s;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("s")
  public String getS() {
    return s;
  }
  public void setS(String s) {
    this.s = s;
  }

  /**
   **/
  public WithdrawReq v(java.math.BigInteger v) {
    this.v = v;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("v")
  public java.math.BigInteger getV() {
    return v;
  }
  public void setV(java.math.BigInteger v) {
    this.v = v;
  }

  /**
   **/
  public WithdrawReq r(String r) {
    this.r = r;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("r")
  public String getR() {
    return r;
  }
  public void setR(String r) {
    this.r = r;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithdrawReq withdrawReq = (WithdrawReq) o;
    return Objects.equals(address, withdrawReq.address) &&
        Objects.equals(amount, withdrawReq.amount) &&
        Objects.equals(token, withdrawReq.token) &&
        Objects.equals(nonce, withdrawReq.nonce) &&
        Objects.equals(s, withdrawReq.s) &&
        Objects.equals(v, withdrawReq.v) &&
        Objects.equals(r, withdrawReq.r);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, amount, token, nonce, s, v, r);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class WithdrawReq {\n");
    
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
    sb.append("    nonce: ").append(toIndentedString(nonce)).append("\n");
    sb.append("    s: ").append(toIndentedString(s)).append("\n");
    sb.append("    v: ").append(toIndentedString(v)).append("\n");
    sb.append("    r: ").append(toIndentedString(r)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

