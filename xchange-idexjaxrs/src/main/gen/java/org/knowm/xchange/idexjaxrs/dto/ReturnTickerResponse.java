package org.knowm.xchange.idexjaxrs.dto;

import javax.validation.constraints.*;
import javax.validation.Valid;


import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;


public class ReturnTickerResponse   {
  
  private @Valid String last = "";
  private @Valid String high = "";
  private @Valid String low = "";
  private @Valid String lowestAsk = "";
  private @Valid String highestBid = "";
  private @Valid String percentChange = "";
  private @Valid String baseVolume = "";
  private @Valid String quoteVolume = "";

  /**
   **/
  public ReturnTickerResponse last(String last) {
    this.last = last;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("last")
  public String getLast() {
    return last;
  }
  public void setLast(String last) {
    this.last = last;
  }

  /**
   **/
  public ReturnTickerResponse high(String high) {
    this.high = high;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("high")
  public String getHigh() {
    return high;
  }
  public void setHigh(String high) {
    this.high = high;
  }

  /**
   **/
  public ReturnTickerResponse low(String low) {
    this.low = low;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("low")
  public String getLow() {
    return low;
  }
  public void setLow(String low) {
    this.low = low;
  }

  /**
   **/
  public ReturnTickerResponse lowestAsk(String lowestAsk) {
    this.lowestAsk = lowestAsk;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("lowestAsk")
  public String getLowestAsk() {
    return lowestAsk;
  }
  public void setLowestAsk(String lowestAsk) {
    this.lowestAsk = lowestAsk;
  }

  /**
   **/
  public ReturnTickerResponse highestBid(String highestBid) {
    this.highestBid = highestBid;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("highestBid")
  public String getHighestBid() {
    return highestBid;
  }
  public void setHighestBid(String highestBid) {
    this.highestBid = highestBid;
  }

  /**
   **/
  public ReturnTickerResponse percentChange(String percentChange) {
    this.percentChange = percentChange;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("percentChange")
  public String getPercentChange() {
    return percentChange;
  }
  public void setPercentChange(String percentChange) {
    this.percentChange = percentChange;
  }

  /**
   **/
  public ReturnTickerResponse baseVolume(String baseVolume) {
    this.baseVolume = baseVolume;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("baseVolume")
  public String getBaseVolume() {
    return baseVolume;
  }
  public void setBaseVolume(String baseVolume) {
    this.baseVolume = baseVolume;
  }

  /**
   **/
  public ReturnTickerResponse quoteVolume(String quoteVolume) {
    this.quoteVolume = quoteVolume;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("quoteVolume")
  public String getQuoteVolume() {
    return quoteVolume;
  }
  public void setQuoteVolume(String quoteVolume) {
    this.quoteVolume = quoteVolume;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReturnTickerResponse returnTickerResponse = (ReturnTickerResponse) o;
    return Objects.equals(last, returnTickerResponse.last) &&
        Objects.equals(high, returnTickerResponse.high) &&
        Objects.equals(low, returnTickerResponse.low) &&
        Objects.equals(lowestAsk, returnTickerResponse.lowestAsk) &&
        Objects.equals(highestBid, returnTickerResponse.highestBid) &&
        Objects.equals(percentChange, returnTickerResponse.percentChange) &&
        Objects.equals(baseVolume, returnTickerResponse.baseVolume) &&
        Objects.equals(quoteVolume, returnTickerResponse.quoteVolume);
  }

  @Override
  public int hashCode() {
    return Objects.hash(last, high, low, lowestAsk, highestBid, percentChange, baseVolume, quoteVolume);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReturnTickerResponse {\n");
    
    sb.append("    last: ").append(toIndentedString(last)).append("\n");
    sb.append("    high: ").append(toIndentedString(high)).append("\n");
    sb.append("    low: ").append(toIndentedString(low)).append("\n");
    sb.append("    lowestAsk: ").append(toIndentedString(lowestAsk)).append("\n");
    sb.append("    highestBid: ").append(toIndentedString(highestBid)).append("\n");
    sb.append("    percentChange: ").append(toIndentedString(percentChange)).append("\n");
    sb.append("    baseVolume: ").append(toIndentedString(baseVolume)).append("\n");
    sb.append("    quoteVolume: ").append(toIndentedString(quoteVolume)).append("\n");
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

