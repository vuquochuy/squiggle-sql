package examples;

import com.truemesh.squiggle.*;
import com.truemesh.squiggle.criteria.InCriteria;
import com.truemesh.squiggle.criteria.MatchCriteria;

public class Sample002WhereCriteria {
    public static void main(String[] args) {

        Table people = new Table("people");

        SelectQuery select = new SelectQuery();

        select.addColumn(people, "firstname");
        select.addColumn(people, "lastname");

        select.addCriteria(
                new MatchCriteria(people, "age", MatchCriteria.GREATER, "30"));

        select.addCriteria(
                new InCriteria(people, "department", new String[] {"I.T.", "Cooking"}));

        System.out.println(select);

    }
}

// Result:
//
// SELECT
//     people.firstname ,
//     people.lastname
// FROM
//     people
// WHERE
//     people.age > '30' AND
//     people.department IN (
//         'I.T.','Cooking'
//     )