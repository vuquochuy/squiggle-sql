package com.truemesh.squiggle.tests;

import com.truemesh.squiggle.*;
import static com.truemesh.squiggle.tests.SqlMatcher.generatesSql;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class Test005Join {
    @Test
    public void join() {
        Table people = new Table("people");
        Table departments = new Table("departments");

        SelectQuery select = new SelectQuery(people); // base table

        select.addColumn(people, "firstname");
        select.addColumn(departments, "director");

        select.addJoin(people, "departmentID", departments, "id");

        assertThat(select, generatesSql(
                "SELECT " +
                "    people.firstname , " +
                "    departments.director " +
                "FROM " +
                "    people , " +
                "    departments " +
                "WHERE " +
                "    people.departmentID = departments.id"));
    }
}