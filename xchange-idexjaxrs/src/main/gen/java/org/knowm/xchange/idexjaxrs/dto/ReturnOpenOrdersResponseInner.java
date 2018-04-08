package org.knowm.xchange.idexjaxrs.dto;

import org.knowm.xchange.idexjaxrs.dto.ReturnOpenOrdersResponseInnerParams;
import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnOpenOrdersResponseInner   {
  
  private @Valid java.math.BigInteger orderNumber = null;
  private @Valid String orderHash = "";
  private @Valid String price = "";
  private @Valid String amount = "";
  private @Valid String total = "";
  private @Valid String type = "";
  private @Valid ReturnOpenOrdersResponseInnerParams params = null;

  /**
   **/
  public ReturnOpenOrdersResponseInner orderNumber(java.math.BigInteger orderNumber) {
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
  public ReturnOpenOrdersResponseInner orderHash(String orderHash) {
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
  public ReturnOpenOrdersResponseInner price(String price) {
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
  public ReturnOpenOrdersResponseInner amount(String amount) {
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
  public ReturnOpenOrdersResponseInner total(String total) {
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
  public ReturnOpenOrdersResponseInner type(String type) {
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
  public ReturnOpenOrdersResponseInner params(ReturnOpenOrdersResponseInnerParams params) {
    this.params = params;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("params")
  public ReturnOpenOrdersResponseInnerParams getParams() {
    return params;
  }
  public void setParams(ReturnOpenOrdersResponseInnerParams params) {
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
    ReturnOpenOrdersResponseInner returnOpenOrdersResponseInner = (ReturnOpenOrdersResponseInner) o;
    return Objects.equals(orderNumber, returnOpenOrdersResponseInner.orderNumber) &&
        Objects.equals(orderHash, returnOpenOrdersResponseInner.orderHash) &&
        Objects.equals(price, returnOpenOrdersResponseInner.price) &&
        Objects.equals(amount, returnOpenOrdersResponseInner.amount) &&
        Objects.equals(total, returnOpenOrdersResponseInner.total) &&
        Objects.equals(type, returnOpenOrdersResponseInner.type) &&
        Objects.equals(params, returnOpenOrdersResponseInner.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderNumber, orderHash, price, amount, total, type, params);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnOpenOrdersResponseInner {\n");
    
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

