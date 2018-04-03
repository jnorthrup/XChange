package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CancelResponse   {
  
  private @Valid java.math.BigInteger success = null;
  private @Valid String error = null;

  /**
   **/
  public CancelResponse success(java.math.BigInteger success) {
    this.success = success;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("success")
  public java.math.BigInteger getSuccess() {
    return success;
  }
  public void setSuccess(java.math.BigInteger success) {
    this.success = success;
  }

  /**
   * wishful thinking
   **/
  public CancelResponse error(String error) {
    this.error = error;
    return this;
  }

  
  @ApiModelProperty(value = "wishful thinking")
  @JsonProperty("error")
  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CancelResponse cancelResponse = (CancelResponse) o;
    return Objects.equals(success, cancelResponse.success) &&
        Objects.equals(error, cancelResponse.error);
  }

  @Override
  public int hashCode() {
    return Objects.hash(success, error);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CancelResponse {\n");
    
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

