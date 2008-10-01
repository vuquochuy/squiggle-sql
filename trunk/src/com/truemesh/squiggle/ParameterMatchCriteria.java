package com.truemesh.squiggle;


/**
 * Class ParameterMatchCriteria is a Criteria class extension that generates the
 * SQL syntax for a parameter condition in an SQL Where clause.
 * 
 * @author <a href="mailto:derek@derekmahar.ca">Derek Mahar</a>
 * @deprecated just use a {@link MatchCriteria} and pass a {@link Parameter} to its constructor
 */
public class ParameterMatchCriteria extends MatchCriteria {
  /**
   * Initializes a ParameterMatchCriter with the table column and comparison
   * operator to use in the condition.
   * 
   * @param column
   *            the Column object that represents the table column to use in the
   *            SQL query condition.
   * 
   * @param comparisonOperator
   *            the comparison operator to use in the SQL query condition. May
   *            be one of EQUALS, GREATER, GREATEREQUAL, LESS, LESSEQUAL, or
   *            LIKE.
   */
  public ParameterMatchCriteria(Matchable column, String comparisonOperator) {
	  super(column, comparisonOperator, new Parameter());
  }
}