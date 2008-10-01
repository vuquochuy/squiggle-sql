package com.truemesh.squiggle;


/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce added join operator
 * @deprecated just use a {@link MatchCriteria} and pass two columns to its constructor
 */
public class JoinCriteria extends MatchCriteria {
    public JoinCriteria(Column source, Column dest) {
        this(source, MatchCriteria.EQUALS, dest);
    }
    
    public JoinCriteria(Column source, String operator, Column dest) {
    	super(source, operator, dest);
    }
}
