package com.truemesh.squiggle;

import com.truemesh.squiggle.output.Output;

import java.util.Set;

public class FunctionCall implements Matchable, Selectable {
    private final String functionName;

    public FunctionCall(String functionName) {
        this.functionName = functionName;
    }

    public void write(Output out) {
        out.print(functionName).print("()");
    }

    public void addReferencedTablesTo(Set<Table> tables) {
    }
}
