package com.truemesh.squiggle;

import java.util.Collection;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;

import com.truemesh.squiggle.output.Output;
import com.truemesh.squiggle.literal.StringLiteral;
import com.truemesh.squiggle.literal.IntegerLiteral;
import com.truemesh.squiggle.literal.FloatLiteral;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce
 */
public class InCriteria extends Criteria {
    private Matchable matched;
    private Collection<Literal> literals;
    private SelectQuery subSelect;

    public InCriteria(Matchable column, Collection<Literal> literals) {
        this.matched = column;
        this.literals = literals;
    }

    public InCriteria(Matchable column, String... values) {
        this.matched = column;
        this.literals = new ArrayList<Literal>();
        for (String value : values) literals.add(new StringLiteral(value));
    }

    public InCriteria(Matchable column, long... values) {
        this.matched = column;
        this.literals = new ArrayList<Literal>();
        for (long value : values) literals.add(new IntegerLiteral(value));
    }

    public InCriteria(Matchable column, double... values) {
        this.matched = column;
        this.literals = new ArrayList<Literal>();
        for (double value : values) literals.add(new FloatLiteral(value));
    }

    public InCriteria(Matchable column, SelectQuery subSelect) {
        this.matched = column;
        this.subSelect = subSelect;
    }

    public InCriteria(Table table, String columnname, Collection<Literal> literals) {
        this(table.getColumn(columnname), literals);
    }

    public InCriteria(Table table, String columnname, String[] values) {
        this(table.getColumn(columnname), values);
    }

    public InCriteria(Table table, String columnname, double[] values) {
        this(table.getColumn(columnname), values);
    }

    public InCriteria(Table table, String columnname, long[] values) {
        this(table.getColumn(columnname), values);
    }

    public InCriteria(Table table, String columnname, SelectQuery subSelect) {
        this(table.getColumn(columnname), subSelect);
    }

    public Matchable getMatched() {
        return matched;
    }

    public void write(Output out) {
        out.print(matched);
        out.println(" IN (");
        out.indent();

        if (subSelect != null) {
            subSelect.write(out);
        } else {
            for (Iterator<Literal> it = literals.iterator(); it.hasNext();) {
                Literal literal = it.next();
                literal.write(out);
                if (it.hasNext()) {
                    out.print(", ");
                }
            }
        }

        out.println();
        out.unindent();
        out.print(")");
    }

    public void addReferencedTablesTo(Set<Table> tables) {
        matched.addReferencedTablesTo(tables);
    }
}
