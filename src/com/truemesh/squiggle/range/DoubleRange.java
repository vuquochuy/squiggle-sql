package com.truemesh.squiggle.range;

public class DoubleRange {
  private Double end;

  private Double start;

  public DoubleRange(double start, double end) {
    this.start = new Double(start);
    this.end = new Double(end);
  }

  public DoubleRange(Double start, Double end) {
    this.start = start;
    this.end = end;
  }

  public Double getEnd() {
    return end;
  }

  public Double getStart() {
    return start;
  }
}
