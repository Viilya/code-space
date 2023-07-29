package com.ssafy.model;

public class Student extends Person{
    private String major;

    public Student(){}
    public Student(String id, int ban, String name, String major) {
        super(id, ban, name);
        this.major = major;
    }
    public String getMajor() {
        return this.major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return super.toString() + 
            "Student = [ " + getMajor() + "'" +
            "}";
    }
    
}
