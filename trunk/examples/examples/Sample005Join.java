package examples;

import com.truemesh.squiggle.*;

public class Sample005Join {
    public static void main(String[] args) {

        Table people = new Table("people");
        Table departments = new Table("departments");

        SelectQuery select = new SelectQuery(); // base table

        select.addColumn(people, "firstname");
        select.addColumn(departments, "director");

        select.addJoin(people, "departmentID", departments, "id");

        System.out.println(select);

    }
}

// Result:
//
// SELECT
//     people.firstname ,
//     departments.director
// FROM
//     people ,
//     departments
// WHERE
//     people.departmentID = departments.id
