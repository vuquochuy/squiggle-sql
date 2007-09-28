package com.truemesh.squiggle.range;

public class IntegerRange {
  private Integer end;

  private Integer start;

  public IntegerRange(int start, int end) {
    this.start = new Integer(start);
    this.end = new Integer(end);
  }

  public IntegerRange(Integer start, Integer end) {
    this.start = start;
    this.end = end;
  }

  public Integer getEnd() {
    return end;
  }

  public Integer getStart() {
    return start;
  }
}
