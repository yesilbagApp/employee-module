package com.employee.api.constants;

public class ApiGroups {

    private ApiGroups() {

    }

    public static final class Employee {
        public static final String NAME = "employee-api";
        public static final String PATHS = "/api/employee/**";
        public static final String TITLE = "EmployeeAPI";
        public static final String DESC = "Employee API";

        private Employee() {

        }
    }
}
