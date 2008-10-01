package examples;

import com.truemesh.squiggle.*;

public class Sample004SelectWildcardColumn {
    public static void main(String[] args) {

        Table people = new Table("people");

        SelectQuery select = new SelectQuery(people);

        select.addToSelection(new WildCardColumn(people));

        System.out.println(select);

    }
}

// Result:
//
// SELECT
//     people.*
// FROM
//     people