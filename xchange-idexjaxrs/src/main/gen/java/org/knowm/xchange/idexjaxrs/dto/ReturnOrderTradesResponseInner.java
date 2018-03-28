package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnOrderTradesResponseInner   {
  
  private @Valid String date = "";
  private @Valid String amount = "";
  private @Valid String type = "";
  private @Valid String total = "";
  private @Valid String price = "";
  private @Valid String uuid = "";
  private @Valid String transactionHash = "";

  /**
   **/
  public ReturnOrderTradesResponseInner date(String date) {
    this.date = date;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("date")
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }

  /**
   **/
  public ReturnOrderTradesResponseInner amount(String amount) {
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
  public ReturnOrderTradesResponseInner type(String type) {
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
  public ReturnOrderTradesResponseInner total(String total) {
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
  public ReturnOrderTradesResponseInner price(String price) {
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
  public ReturnOrderTradesResponseInner uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("uuid")
  public String getUuid() {
    return uuid;
  }
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /**
   **/
  public ReturnOrderTradesResponseInner transactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("transactionHash")
  public String getTransactionHash() {
    return transactionHash;
  }
  public void setTransactionHash(String transactionHash) {
    this.transactionHash = transactionHash;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnOrderTradesResponseInner returnOrderTradesResponseInner = (ReturnOrderTradesResponseInner) o;
    return Objects.equals(date, returnOrderTradesResponseInner.date) &&
        Objects.equals(amount, returnOrderTradesResponseInner.amount) &&
        Objects.equals(type, returnOrderTradesResponseInner.type) &&
        Objects.equals(total, returnOrderTradesResponseInner.total) &&
        Objects.equals(price, returnOrderTradesResponseInner.price) &&
        Objects.equals(uuid, returnOrderTradesResponseInner.uuid) &&
        Objects.equals(transactionHash, returnOrderTradesResponseInner.transactionHash);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, amount, type, total, price, uuid, transactionHash);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnOrderTradesResponseInner {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    transactionHash: ").append(toIndentedString(transactionHash)).append("\n");
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

