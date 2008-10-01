package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.*;
import org.junit.Test;
import static org.junit.Assert.assertThat;

// Result:
//
// SELECT
//     user.*
// FROM
//     user
// WHERE
//     ( user.name LIKE 'a%' OR ( user.id = 12345 AND user.feet = 'smelly' ) )

public class Test009OrAnd {
    @Test
    public void orAnd() {
        Table user = new Table("user");

        SelectQuery select = new SelectQuery(user);

        select.addToSelection(new WildCardColumn(user));

        Criteria name = new MatchCriteria(user, "name", MatchCriteria.LIKE, "a%");
        Criteria id = new MatchCriteria(user, "id", MatchCriteria.EQUALS, 12345);
        Criteria feet = new MatchCriteria(user, "feet", MatchCriteria.EQUALS, "smelly");

        select.addCriteria(new OR(name, new AND(id, feet)));

        assertThat(select, SqlMatcher.generatesSql(
                "SELECT " +
                "    user.* " +
                "FROM " +
                "    user " +
                "WHERE " +
                "    ( user.name LIKE 'a%' OR ( user.id = 12345 AND user.feet = 'smelly' ) )"));
    }
}