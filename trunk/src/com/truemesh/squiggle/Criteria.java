package com.truemesh.squiggle;

import java.util.Set;

import com.truemesh.squiggle.output.ToStringer;
import com.truemesh.squiggle.output.Outputable;
import com.truemesh.squiggle.output.Output;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce
 */
public abstract class Criteria implements Outputable {
    public abstract void write(Output out);

    public String toString() {
        return ToStringer.toString(this);
    }
    
	public abstract void addReferencedTablesTo(Set<Table> tables);
}
