package org.knowm.xchange.idexjaxrs.dto;

import org.knowm.xchange.idexjaxrs.dto.InlineResponse200Params;
import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class InlineResponse200   {
  
  private @Valid String error = null;
  private @Valid java.math.BigInteger orderNumber = null;
  private @Valid String orderHash = "";
  private @Valid String price = "";
  private @Valid String amount = "";
  private @Valid String total = "";
  private @Valid String type = "";
  private @Valid InlineResponse200Params params = null;

  /**
   **/
  public InlineResponse200 error(String error) {
    this.error = error;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("error")
  public String getError() {
    return error;
  }
  public void setError(String error) {
    this.error = error;
  }

  /**
   **/
  public InlineResponse200 orderNumber(java.math.BigInteger orderNumber) {
    this.orderNumber = orderNumber;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("orderNumber")
  public java.math.BigInteger getOrderNumber() {
    return orderNumber;
  }
  public void setOrderNumber(java.math.BigInteger orderNumber) {
    this.orderNumber = orderNumber;
  }

  /**
   **/
  public InlineResponse200 orderHash(String orderHash) {
    this.orderHash = orderHash;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("orderHash")
  public String getOrderHash() {
    return orderHash;
  }
  public void setOrderHash(String orderHash) {
    this.orderHash = orderHash;
  }

  /**
   **/
  public InlineResponse200 price(String price) {
    this.price = price;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("price")
  public String getPrice() {
    return price;
  }
  public void setPrice(String price) {
    this.price = price;
  }

  /**
   **/
  public InlineResponse200 amount(String amount) {
    this.amount = amount;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("amount")
  public String getAmount() {
    return amount;
  }
  public void setAmount(String amount) {
    this.amount = amount;
  }

  /**
   **/
  public InlineResponse200 total(String total) {
    this.total = total;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("total")
  public String getTotal() {
    return total;
  }
  public void setTotal(String total) {
    this.total = total;
  }

  /**
   **/
  public InlineResponse200 type(String type) {
    this.type = type;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  /**
   **/
  public InlineResponse200 params(InlineResponse200Params params) {
    this.params = params;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("params")
  public InlineResponse200Params getParams() {
    return params;
  }
  public void setParams(InlineResponse200Params params) {
    this.params = params;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InlineResponse200 inlineResponse200 = (InlineResponse200) o;
    return Objects.equals(error, inlineResponse200.error) &&
        Objects.equals(orderNumber, inlineResponse200.orderNumber) &&
        Objects.equals(orderHash, inlineResponse200.orderHash) &&
        Objects.equals(price, inlineResponse200.price) &&
        Objects.equals(amount, inlineResponse200.amount) &&
        Objects.equals(total, inlineResponse200.total) &&
        Objects.equals(type, inlineResponse200.type) &&
        Objects.equals(params, inlineResponse200.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(error, orderNumber, orderHash, price, amount, total, type, params);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");
    
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
    sb.append("    orderHash: ").append(toIndentedString(orderHash)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    params: ").append(toIndentedString(params)).append("\n");
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

