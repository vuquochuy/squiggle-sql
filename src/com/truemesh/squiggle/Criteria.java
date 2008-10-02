package com.truemesh.squiggle;

import com.truemesh.squiggle.output.ToStringer;
import com.truemesh.squiggle.output.Outputable;
import com.truemesh.squiggle.output.Output;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce
 */
public abstract class Criteria implements Outputable, CanReferToTables {
    public abstract void write(Output out);

    public String toString() {
        return ToStringer.toString(this);
    }
}
