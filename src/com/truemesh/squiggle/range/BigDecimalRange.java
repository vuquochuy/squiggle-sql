package com.truemesh.squiggle.range;

import java.math.BigDecimal;

public class BigDecimalRange {
  private BigDecimal end;

  private BigDecimal start;

  public BigDecimalRange(BigDecimal start, BigDecimal end) {
    this.start = start;
    this.end = end;
  }

  public BigDecimal getEnd() {
    return end;
  }

  public BigDecimal getStart() {
    return start;
  }
}
