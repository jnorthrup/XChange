package org.knowm.xchange.idexjaxrs.dto;

import java.math.BigDecimal;
import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class CompleteBalance   {
  
  private @Valid BigDecimal available = null;
  private @Valid BigDecimal onOrders = null;

  /**
   **/
  public CompleteBalance available(BigDecimal available) {
    this.available = available;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("available")
  public BigDecimal getAvailable() {
    return available;
  }
  public void setAvailable(BigDecimal available) {
    this.available = available;
  }

  /**
   **/
  public CompleteBalance onOrders(BigDecimal onOrders) {
    this.onOrders = onOrders;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("onOrders")
  public BigDecimal getOnOrders() {
    return onOrders;
  }
  public void setOnOrders(BigDecimal onOrders) {
    this.onOrders = onOrders;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompleteBalance completeBalance = (CompleteBalance) o;
    return Objects.equals(available, completeBalance.available) &&
        Objects.equals(onOrders, completeBalance.onOrders);
  }

  @Override
  public int hashCode() {
    return Objects.hash(available, onOrders);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompleteBalance {\n");
    
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    onOrders: ").append(toIndentedString(onOrders)).append("\n");
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

