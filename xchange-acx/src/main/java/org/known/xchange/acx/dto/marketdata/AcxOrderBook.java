package org.known.xchange.acx.dto.marketdata;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcxOrderBook {
  public final List<AcxOrder> bids;
  public final List<AcxOrder> asks;

  public AcxOrderBook(@JsonProperty("bids") List<AcxOrder> bids, //
      @JsonProperty("asks") List<AcxOrder> asks) {
    this.bids = Collections.unmodifiableList(bids);
    this.asks = Collections.unmodifiableList(asks);
  }
}
