package com.ssafy.model;

public class Instructor extends Person {
    private String subject;

    public Instructor(String id, int ban, String name, String subject) {
        super(id, ban, name);
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    @Override
    public String toString() {
        return super.toString() + " {" +
            " subject='" + getSubject() + "'" +
            "}";
    }
    
}
