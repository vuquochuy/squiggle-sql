package com.truemesh.squiggle.range;

public class FloatRange {
  private Float end;

  private Float start;

  public FloatRange(float start, float end) {
    this.start = new Float(start);
    this.end = new Float(end);
  }

  public FloatRange(Float start, Float end) {
    this.start = start;
    this.end = end;
  }

  public Float getEnd() {
    return end;
  }

  public Float getStart() {
    return start;
  }
}
