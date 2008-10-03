package examples;

import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
import com.truemesh.squiggle.Order;

public class Sample001SimplestSelect {
    public static void main(String[] args) {

        Table people = new Table("people");

        SelectQuery select = new SelectQuery();

        select.addColumn(people, "firstname");
        select.addColumn(people, "lastname");

        select.addOrder(people, "age", Order.DESCENDING);

        System.out.println(select);

    }
}

// Result:
//
// SELECT
//    people.firstname ,
//    people.lastname
// FROM
//     people
// ORDER BY
//     people.age DESC
