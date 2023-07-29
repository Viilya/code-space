package com.ssafy.model;

import java.util.Objects;

abstract public class Person {
    private String id;
    private int ban;
    private String name;

    public Person(String id, int ban, String name){
        super();
        this.id =id;
        this.ban = ban;
        this.name = name;
    }

    public Person() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBan() {
        return this.ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person id(String id) {
        setId(id);
        return this;
    }

    public Person ban(int ban) {
        setBan(ban);
        return this;
    }

    public Person name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && ban == person.ban && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ban, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", ban='" + getBan() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

    
    
}
