package examples;

import com.truemesh.squiggle.*;
import com.truemesh.squiggle.criteria.InCriteria;
import com.truemesh.squiggle.criteria.MatchCriteria;

public class Sample007SubSelect {
    public static void main(String[] args) {

        Table people = new Table("people");
        Table taxcodes = new Table("taxcodes");

        SelectQuery select = new SelectQuery(people);
        select.addColumn(people, "firstname");

        SelectQuery subSelect = new SelectQuery(taxcodes);
        subSelect.addColumn(taxcodes, "id");
        subSelect.addCriteria(new MatchCriteria(taxcodes, "valid", MatchCriteria.EQUALS, true));

        select.addCriteria(new InCriteria(people, "taxcode", subSelect));

        System.out.println(select);

    }
}

// Result:
// 
// SELECT
//     people.firstname
// FROM
//     people
// WHERE
//     people.taxcode IN (
//         SELECT
//             taxcodes.id
//         FROM
//             taxcodes
//         WHERE
//             taxcodes.valid = true
//
//     )
