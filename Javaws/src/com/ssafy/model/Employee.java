package com.ssafy.model;

public class Employee extends Person {
    private String department;

    public Employee(String id, int ban, String name, String department) {
        super(id, ban, name);
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return super.toString() + " {" +
            " department='" + getDepartment() + "'" +
            "}";
    }
    
}
