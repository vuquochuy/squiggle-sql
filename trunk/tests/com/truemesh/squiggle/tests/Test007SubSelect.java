package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.*;
import static com.truemesh.squiggle.tests.SqlMatcher.generatesSql;
import static org.junit.Assert.assertThat;

public class Test007SubSelect {
    public static void main(String[] args) {
        Table people = new Table("people");
        Table taxcodes = new Table("taxcodes");

        SelectQuery select = new SelectQuery(people);
        select.addColumn(people, "firstname");

        SelectQuery subSelect = new SelectQuery(taxcodes);
        subSelect.addColumn(taxcodes, "id");
        subSelect.addCriteria(new MatchCriteria(taxcodes, "valid", MatchCriteria.EQUALS, true));

        select.addCriteria(new InCriteria(people, "taxcode", subSelect));

        assertThat(select, generatesSql(
                "SELECT " +
                "    people.firstname " +
                "FROM " +
                "    people " +
                "WHERE " +
                "    people.taxcode IN ( " +
                "        SELECT " +
                "            taxcodes.id " +
                "        FROM " +
                "            taxcodes " +
                "        WHERE " +
                "            taxcodes.valid = true " +
                "    )"));
    }
}
