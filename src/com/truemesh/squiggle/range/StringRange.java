package com.truemesh.squiggle.range;

public class StringRange {
  private String end;

  private String start;

  public StringRange(String start, String end) {
    this.start = start;
    this.end = end;
  }

  public String getEnd() {
    return end;
  }

  public String getStart() {
    return start;
  }
}
