package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnNextNonceResponse   {
  
  private @Valid java.math.BigInteger nonce = null;

  /**
   **/
  public ReturnNextNonceResponse nonce(java.math.BigInteger nonce) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnNextNonceResponse returnNextNonceResponse = (ReturnNextNonceResponse) o;
    return Objects.equals(nonce, returnNextNonceResponse.nonce);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nonce);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnNextNonceResponse {\n");
    
    sb.append("    nonce: ").append(toIndentedString(nonce)).append("\n");
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

