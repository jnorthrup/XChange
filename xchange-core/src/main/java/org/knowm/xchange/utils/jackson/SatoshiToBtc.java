package org.knowm.xchange.utils.jackson;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @author Matija Mazi
 */
public class SatoshiToBtc extends JsonDeserializer<BigDecimal> {

  @Override
  public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
    return new BigDecimal(jp.getValueAsLong()).movePointLeft(8);
  }
}
