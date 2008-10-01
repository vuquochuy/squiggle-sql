package com.truemesh.squiggle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.truemesh.squiggle.output.Output;
import com.truemesh.squiggle.output.Outputable;
import com.truemesh.squiggle.output.ToStringer;

/**
 * @author <a href="joe@truemesh.com">Joe Walnes</a>
 * @author Nat Pryce
 */
public class SelectQuery implements Outputable, CanReferToTables {
    public static final int indentSize = 4;

    private final Table baseTable;
    private final List selection = new ArrayList();
    private final List criteria = new ArrayList();
    private final List order = new ArrayList();

    private boolean isDistinct = false;
    
    public SelectQuery(Table baseTable) {
        this.baseTable = baseTable;
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
    private Set findAllUsedTables() {
    	Set tables = new LinkedHashSet();
    	addReferencedTablesTo(tables);
        return tables;
    }

	public void addReferencedTablesTo(Set tables) {
		tables.add(baseTable);
    	
    	{ // Get all tables used by selection
    		Iterator i = selection.iterator(); 
    		while (i.hasNext()) {
    			((Selectable)i.next()).addReferencedTablesTo(tables);
    		}
    		
    	}
    	
        { // Get all tables used by criteria
            Iterator i = criteria.iterator();
            while (i.hasNext()) {
            	((Criteria)i.next()).addReferencedTablesTo(tables);
            }
        }
        	
        { // Get all tables used by columns
            Iterator i = order.iterator();
            while (i.hasNext()) {
                ((Order)i.next()).addReferencedTablesTo(tables);
            }
        }
	}
}
