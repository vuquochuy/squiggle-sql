package examples;

import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.MatchCriteria;
import com.truemesh.squiggle.InCriteria;

public class Sample008Tutorial {

    public static void main(String[] args) {

        // basic query
        Table orders = new Table("orders_table");
        SelectQuery select = new SelectQuery(orders);

        // add columns
        select.addColumn(orders, "id");
        select.addColumn(orders, "total_price");

        // matches
        select.addCriteria(new MatchCriteria(orders, "status", MatchCriteria.EQUALS, "processed"));
        select.addCriteria(new MatchCriteria(orders, "items", MatchCriteria.LESS, 5));

        // IN...
        select.addCriteria(new InCriteria(orders, "delivery",
            new String[] { "post", "fedex", "goat" } ));

        // join
        Table warehouses = new Table("warehouses_table");
        select.addJoin(orders, "warehouse_id", warehouses, "id");

        // use joined table
        select.addColumn(warehouses, "location");
        select.addCriteria(new MatchCriteria(warehouses, "size", MatchCriteria.EQUALS, "big"));

        // build subselect query
        Table offers = new Table("offers_table");
        SelectQuery subSelect = new SelectQuery(offers);
        subSelect.addColumn(offers, "location");
        subSelect.addCriteria(new MatchCriteria(offers, "valid", MatchCriteria.EQUALS, true));

        // add subselect to original query
        select.addCriteria(new InCriteria(warehouses, "location", subSelect));

        // show result
        System.out.println(select);

    }

}
