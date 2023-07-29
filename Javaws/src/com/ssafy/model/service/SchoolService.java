package com.ssafy.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ssafy.model.*;

public class SchoolService {
    final int MAX_SIZE = 10;
    Person[] persons;
    public int size = 0;
    private List<Person> personList;
    private static SchoolService instance = new SchoolService();

    private SchoolService() {
        persons = new Person[MAX_SIZE];
        personList = new ArrayList<Person>();
    }

    public static SchoolService getInstance() {
        return instance;
    }

    public void add(Person person) {
        persons[size++] = person;
        personList.add(person);
    }
    

    /**
     * 
     * @return
     */
    public Person[] getPersons() {
        return Arrays.copyOfRange(persons, 0, size);
    }
}
