package com.truemesh.squiggle;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.truemesh.squiggle.Column;
import com.truemesh.squiggle.Criteria;
import com.truemesh.squiggle.MatchCriteria;
import com.truemesh.squiggle.output.Output;

/**
 * Class DateMatchCriteria is a Criteria that generates a comparison between a
 * table column and a date literal in an SQL Where clause.
 * 
 * @author <a href="mailto:derek@derekmahar.ca">Derek Mahar</a>
 */
public class DateMatchCriteria extends Criteria {
  private MatchCriteria criteria;

  /**
   * Initializes a DateMatchCriteria with a given column, comparison operator,
   * and date operand that the criteria will use to make a comparison between
   * the given column and the date.
   * 
   * @param column
   *            the column to use in the date comparison.
   * 
   * @param operator
   *            the comparison operator to use in the date comparison.
   * 
   * @param operand
   *            the date literal to use in the comparison.
   */
  public DateMatchCriteria(Column column, String operator, Date operand) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    String dateString = dateFormat.format(operand);
    this.criteria = new MatchCriteria(column, operator, dateString);
  }

  /**
   * Writes this DateMatchCriteria to the given output in the form column,
   * operator, then date literal. The date literal is a string in the form in
   * the form "yyyy-MM-dd HH:mm:ss.S".
   * 
   * @see com.truemesh.squiggle.Criteria#write(com.truemesh.squiggle.output.Output)
   */
  public void write(Output out) {
    criteria.write(out);
  }
}
