package examples;

import com.truemesh.squiggle.*;

public class Sample006TableAliases {
    public static void main(String[] args) {

        // This joins the people table to the people table, using aliases.

        Table employees = new Table("people", "employees");
        Table managers = new Table("people", "managers");

        SelectQuery select = new SelectQuery(employees); // base table

        select.addColumn(employees, "firstname");
        select.addColumn(managers, "firstname");

        select.addJoin(employees, "managerID", managers, "id");

        System.out.println(select);

    }
}

// Result:
//
// SELECT
//     employees.firstname ,
//     managers.firstname
// FROM
//     people employees ,
//     people managers
// WHERE
//     employees.managerID = managers.id