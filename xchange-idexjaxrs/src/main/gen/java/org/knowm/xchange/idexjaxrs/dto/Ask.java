package org.knowm.xchange.idexjaxrs.dto;

import org.knowm.xchange.idexjaxrs.dto.AskParams;
import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Ask   {
  
  private @Valid String price = "";
  private @Valid String amount = "";
  private @Valid String total = "";
  private @Valid String orderHash = "";
  private @Valid AskParams params = null;

  /**
   **/
  public Ask price(String price) {
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
  public Ask amount(String amount) {
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
  public Ask total(String total) {
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
  public Ask orderHash(String orderHash) {
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
  public Ask params(AskParams params) {
    this.params = params;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("params")
  public AskParams getParams() {
    return params;
  }
  public void setParams(AskParams params) {
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
    Ask ask = (Ask) o;
    return Objects.equals(price, ask.price) &&
        Objects.equals(amount, ask.amount) &&
        Objects.equals(total, ask.total) &&
        Objects.equals(orderHash, ask.orderHash) &&
        Objects.equals(params, ask.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(price, amount, total, orderHash, params);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ask {\n");
    
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    orderHash: ").append(toIndentedString(orderHash)).append("\n");
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

