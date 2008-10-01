package com.truemesh.squiggle;

import com.truemesh.squiggle.output.Output;
import com.truemesh.squiggle.output.Outputable;
import com.truemesh.squiggle.output.ToStringer;

import java.util.*;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 */
public class SelectQuery implements Outputable {

    public static final int indentSize = 4;

    private Table baseTable;
    private List selection;
    private boolean isDistinct = false;
    private List criteria;
    private List order;

    public SelectQuery(Table baseTable) {
        this.baseTable = baseTable;
        selection = new ArrayList();
        criteria = new ArrayList();
        order = new ArrayList();
    }

    public Table getBaseTable() {
        return baseTable;
    }
    
    public void addToSelection(Selectable selectable) {
    	selection.add(selectable);
    }
    
    /**
     * Syntax sugar for addColumn(Column).
     */
    public void addColumn(Table table, String columname) {
        addToSelection(table.getColumn(columname));
    }

    public void removeFromSelection(Selectable selectable) {
        selection.remove(selectable);
    }

    /**
     * @return a list of {@link Selectable} objects.
     */
    public List listSelection() {
        return Collections.unmodifiableList(selection);
    }
    
    public boolean isDistinct() {
        return isDistinct;
    }

    public void setDistinct(boolean distinct) {
        isDistinct = distinct;
    }

    public void addCriteria(Criteria criteria) {
        this.criteria.add(criteria);
    }

    public void removeCriteria(Criteria criteria) {
        this.criteria.remove(criteria);
    }

    public List listCriteria() {
        return Collections.unmodifiableList(criteria);
    }

    /**
     * Syntax sugar for addCriteria(JoinCriteria)
     */
    public void addJoin(Table srcTable, String srcColumnname, Table destTable, String destColumnname) {
        addCriteria(new JoinCriteria(srcTable.getColumn(srcColumnname), destTable.getColumn(destColumnname)));
    }

    /**
     * Syntax sugar for addCriteria(JoinCriteria)
     */
    public void addJoin(Table srcTable, String srcColumnName, String operator, Table destTable, String destColumnName) {
        addCriteria(new JoinCriteria(srcTable.getColumn(srcColumnName), operator, destTable.getColumn(destColumnName)));
    }
    
    public void addOrder(Order order) {
        this.order.add(order);
    }

    /**
     * Syntax sugar for addOrder(Order).
     */
    public void addOrder(Table table, String columnname, boolean ascending) {
        addOrder(new Order(table.getColumn(columnname), ascending));
    }

    public void removeOrder(Order order) {
        this.order.remove(order);
    }

    public List listOrder() {
        return Collections.unmodifiableList(order);
    }

    public String toString() {
        return ToStringer.toString(this);
    }

    public void write(Output out) {

        out.print("SELECT");
        if (isDistinct) {
            out.print(" DISTINCT");
        }
        out.println();

        // Add columns to select
        out.indent();
        appendList(out, selection, ",");
        out.unindent();

        // Add tables to select from
        out.println("FROM");

        // Determine all tables used in query
        out.indent();
        appendList(out, findAllUsedTables(), ",");
        out.unindent();

        // Add criteria
        if (criteria.size() > 0) {
            out.println("WHERE");
            out.indent();
            appendList(out, criteria, "AND");
            out.unindent();
        }

        // Add order
        if (order.size() > 0) {
            out.println("ORDER BY");
            out.indent();
            appendList(out, order, ",");
            out.unindent();
        }
    }

    /**
     * Iterate through a Collection and append all entries (using .toString()) to
     * a StringBuffer.
     */
    private void appendList(Output out, Collection collection, String seperator) {
        Iterator i = collection.iterator();
        boolean hasNext = i.hasNext();

        while (hasNext) {
            Outputable curr = (Outputable) i.next();
            hasNext = i.hasNext();
            curr.write(out);
            out.print(' ');
            if (hasNext) {
                out.print(seperator);
            }
            out.println();
        }
    }

    /**
     * Find all the tables used in the query (from columns, criteria and order).
     *
     * @return List of {@link com.truemesh.squiggle.Table}s
     */
    private List findAllUsedTables() {

        List allTables = new ArrayList();
        allTables.add(baseTable);

        { // Get all tables used by columns
            Iterator i = selection.iterator();
            while (i.hasNext()) {
                Table curr = ((Projection) i.next()).getTable();
                if (!allTables.contains(curr)) {
                    allTables.add(curr);
                }
            }
        }

        { // Get all tables used by criteria
            Iterator i = criteria.iterator();
            while (i.hasNext()) {
                try {
                    JoinCriteria curr = (JoinCriteria) i.next();
                    if (!allTables.contains(curr.getSource().getTable())) {
                        allTables.add(curr.getSource().getTable());
                    }
                    if (!allTables.contains(curr.getDest().getTable())) {
                        allTables.add(curr.getDest().getTable());
                    }
                } catch (ClassCastException e) {
                } // not a JoinCriteria
            }
        }

        { // Get all tables used by columns
            Iterator i = order.iterator();
            while (i.hasNext()) {
                Order curr = (Order) i.next();
                Table c = curr.getColumn().getTable();
                if (!allTables.contains(c)) {
                    allTables.add(c);
                }
            }
        }

        return allTables;
    }
}
