package examples;

import com.truemesh.squiggle.*;

public class Sample003NastyStrings {
    public static void main(String[] args) {

        Table people = new Table("people");

        SelectQuery select = new SelectQuery(people);

        select.addColumn(people, "firstname");

        select.addCriteria(
                new MatchCriteria(people, "foo", MatchCriteria.GREATER, "I've got a quote"));

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
//     people.foo > 'I\'ve got a quote'
