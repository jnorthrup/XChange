package org.knowm.xchange.idexjaxrs.dto;

import org.knowm.xchange.idexjaxrs.dto.Ask;
import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnOrderBookResponse   {
  
  private @Valid java.util.List<Ask> asks = new java.util.ArrayList<>();
  private @Valid java.util.List<Ask> bids = new java.util.ArrayList<>();

  /**
   **/
  public ReturnOrderBookResponse asks(java.util.List<Ask> asks) {
    this.asks = asks;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("asks")
  public java.util.List<Ask> getAsks() {
    return asks;
  }
  public void setAsks(java.util.List<Ask> asks) {
    this.asks = asks;
  }

  /**
   **/
  public ReturnOrderBookResponse bids(java.util.List<Ask> bids) {
    this.bids = bids;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("bids")
  public java.util.List<Ask> getBids() {
    return bids;
  }
  public void setBids(java.util.List<Ask> bids) {
    this.bids = bids;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnOrderBookResponse returnOrderBookResponse = (ReturnOrderBookResponse) o;
    return Objects.equals(asks, returnOrderBookResponse.asks) &&
        Objects.equals(bids, returnOrderBookResponse.bids);
  }

  @Override
  public int hashCode() {
    return Objects.hash(asks, bids);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnOrderBookResponse {\n");
    
    sb.append("    asks: ").append(toIndentedString(asks)).append("\n");
    sb.append("    bids: ").append(toIndentedString(bids)).append("\n");
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

