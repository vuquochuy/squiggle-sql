package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.InCriteria;
import com.truemesh.squiggle.MatchCriteria;
import com.truemesh.squiggle.SelectQuery;
import com.truemesh.squiggle.Table;
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
                new MatchCriteria(people, "age", MatchCriteria.GREATER, "30"));

        select.addCriteria(
                new InCriteria(people, "department", new String[] {"I.T.", "Cooking"}));

        assertThat(select, SqlMatcher.generatesSql(
                "SELECT " +
                "    people.firstname , " +
                "    people.lastname " +
                "FROM " +
                "    people " +
                "WHERE " +
                "    people.age > '30' AND " +
                "    people.department IN ( " +
                "        'I.T.','Cooking' " +
                ")"));

    }
}