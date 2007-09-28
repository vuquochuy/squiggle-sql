package com.truemesh.squiggle.range;

import java.util.Date;

public class DateRange {
  private Date start;

  private Date end;

  public DateRange(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public Date getEnd() {
    return end;
  }
}
