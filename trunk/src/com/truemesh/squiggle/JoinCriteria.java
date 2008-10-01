package com.truemesh.squiggle;

import com.truemesh.squiggle.output.Output;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce added join operator
 */
public class JoinCriteria extends Criteria {

    private Column source, dest;
    private String operator;

    public JoinCriteria(Column source, Column dest) {
        this(source, MatchCriteria.EQUALS, dest);
        this.source = source;
        this.dest = dest;
    }
    
    public JoinCriteria(Column source, String operator, Column dest) {
        this.source = source;
        this.dest = dest;
        this.operator = operator;
    }

    public Column getSource() {
        return source;
    }

    public Column getDest() {
        return dest;
    }

    public String getOperator() {
        return operator;
    }
    
    public void write(Output out) {
        out.print(source)
            .print(" ")
            .print(operator)
            .print(" ")
            .print(dest);
    }
}
