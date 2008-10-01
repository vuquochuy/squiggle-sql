package com.truemesh.squiggle;

import com.truemesh.squiggle.output.Output;
import com.truemesh.squiggle.output.ToStringer;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 */
public class Column extends Projection implements Matchable {
	private final String name;
	
    public Column(Table table, String name) {
        super(table);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return ToStringer.toString(this);
    }

    public void write(Output out) {
        out.print(getTable().getAlias()).print('.').print(getName());
    }
}
