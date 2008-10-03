package examples;

import com.truemesh.squiggle.*;
import com.truemesh.squiggle.criteria.AND;
import com.truemesh.squiggle.criteria.MatchCriteria;
import com.truemesh.squiggle.criteria.OR;

public class Sample009OrAnd {
    public static void main(String[] args) {

        Table user = new Table("user");

        SelectQuery select = new SelectQuery();

        select.addToSelection(new WildCardColumn(user));

        Criteria name = new MatchCriteria(user, "name", MatchCriteria.LIKE, "a%");
        Criteria id = new MatchCriteria(user, "id", MatchCriteria.EQUALS, 12345);
        Criteria feet = new MatchCriteria(user, "feet", MatchCriteria.EQUALS, "smelly");

        select.addCriteria(new OR(name, new AND(id, feet)));

        System.out.println(select);

    }

}

// Result:
//
// SELECT
//     user.*
// FROM
//     user
// WHERE
//     ( user.name LIKE 'a%' OR ( user.id = 12345 AND user.feet = 'smelly' ) )
