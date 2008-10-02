package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.*;
import com.truemesh.squiggle.literal.FloatLiteral;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class Test002WhereCriteria {
    @Test
    public void whereCriteria() {
        Table people = new Table("people");

        SelectQuery select = new SelectQuery(people);

        select.addColumn(people, "firstname");
        select.addColumn(people, "lastname");

        select.addCriteria(
                new MatchCriteria(people, "height", MatchCriteria.GREATER, 1.8));
        select.addCriteria(
                new InCriteria(people, "department", new String[] {"I.T.", "Cooking"}));
        select.addCriteria(
                new BetweenCriteria(people.getColumn("age"), 18, 30));

        assertThat(select, SqlMatcher.generatesSql(
                "SELECT " +
                "    people.firstname , " +
                "    people.lastname " +
                "FROM " +
                "    people " +
                "WHERE " +
                "    people.height > 1.8 AND " +
                "    people.department IN ( " +
                "        'I.T.','Cooking' " +
                "    ) AND" +
                "    people.age BETWEEN 18 AND 30"));

    }
}