package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class InlineResponse200Params   {
  
  private @Valid String tokenBuy = "";
  private @Valid java.math.BigInteger buyPrecision = null;
  private @Valid String amountBuy = "";
  private @Valid String tokenSell = "";
  private @Valid java.math.BigInteger sellPrecision = null;
  private @Valid String amountSell = "";
  private @Valid String expires = "0";
  private @Valid java.math.BigInteger nonce = null;
  private @Valid String user = "";

  /**
   **/
  public InlineResponse200Params tokenBuy(String tokenBuy) {
    this.tokenBuy = tokenBuy;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("tokenBuy")
  public String getTokenBuy() {
    return tokenBuy;
  }
  public void setTokenBuy(String tokenBuy) {
    this.tokenBuy = tokenBuy;
  }

  /**
   **/
  public InlineResponse200Params buyPrecision(java.math.BigInteger buyPrecision) {
    this.buyPrecision = buyPrecision;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("buyPrecision")
  public java.math.BigInteger getBuyPrecision() {
    return buyPrecision;
  }
  public void setBuyPrecision(java.math.BigInteger buyPrecision) {
    this.buyPrecision = buyPrecision;
  }

  /**
   **/
  public InlineResponse200Params amountBuy(String amountBuy) {
    this.amountBuy = amountBuy;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("amountBuy")
  public String getAmountBuy() {
    return amountBuy;
  }
  public void setAmountBuy(String amountBuy) {
    this.amountBuy = amountBuy;
  }

  /**
   **/
  public InlineResponse200Params tokenSell(String tokenSell) {
    this.tokenSell = tokenSell;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("tokenSell")
  public String getTokenSell() {
    return tokenSell;
  }
  public void setTokenSell(String tokenSell) {
    this.tokenSell = tokenSell;
  }

  /**
   **/
  public InlineResponse200Params sellPrecision(java.math.BigInteger sellPrecision) {
    this.sellPrecision = sellPrecision;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("sellPrecision")
  public java.math.BigInteger getSellPrecision() {
    return sellPrecision;
  }
  public void setSellPrecision(java.math.BigInteger sellPrecision) {
    this.sellPrecision = sellPrecision;
  }

  /**
   **/
  public InlineResponse200Params amountSell(String amountSell) {
    this.amountSell = amountSell;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("amountSell")
  public String getAmountSell() {
    return amountSell;
  }
  public void setAmountSell(String amountSell) {
    this.amountSell = amountSell;
  }

  /**
   **/
  public InlineResponse200Params expires(String expires) {
    this.expires = expires;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("expires")
  public String getExpires() {
    return expires;
  }
  public void setExpires(String expires) {
    this.expires = expires;
  }

  /**
   **/
  public InlineResponse200Params nonce(java.math.BigInteger nonce) {
    this.nonce = nonce;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("nonce")
  public java.math.BigInteger getNonce() {
    return nonce;
  }
  public void setNonce(java.math.BigInteger nonce) {
    this.nonce = nonce;
  }

  /**
   **/
  public InlineResponse200Params user(String user) {
    this.user = user;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("user")
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200Params inlineResponse200Params = (InlineResponse200Params) o;
    return Objects.equals(tokenBuy, inlineResponse200Params.tokenBuy) &&
        Objects.equals(buyPrecision, inlineResponse200Params.buyPrecision) &&
        Objects.equals(amountBuy, inlineResponse200Params.amountBuy) &&
        Objects.equals(tokenSell, inlineResponse200Params.tokenSell) &&
        Objects.equals(sellPrecision, inlineResponse200Params.sellPrecision) &&
        Objects.equals(amountSell, inlineResponse200Params.amountSell) &&
        Objects.equals(expires, inlineResponse200Params.expires) &&
        Objects.equals(nonce, inlineResponse200Params.nonce) &&
        Objects.equals(user, inlineResponse200Params.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenBuy, buyPrecision, amountBuy, tokenSell, sellPrecision, amountSell, expires, nonce, user);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200Params {\n");
    
    sb.append("    tokenBuy: ").append(toIndentedString(tokenBuy)).append("\n");
    sb.append("    buyPrecision: ").append(toIndentedString(buyPrecision)).append("\n");
    sb.append("    amountBuy: ").append(toIndentedString(amountBuy)).append("\n");
    sb.append("    tokenSell: ").append(toIndentedString(tokenSell)).append("\n");
    sb.append("    sellPrecision: ").append(toIndentedString(sellPrecision)).append("\n");
    sb.append("    amountSell: ").append(toIndentedString(amountSell)).append("\n");
    sb.append("    expires: ").append(toIndentedString(expires)).append("\n");
    sb.append("    nonce: ").append(toIndentedString(nonce)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
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

