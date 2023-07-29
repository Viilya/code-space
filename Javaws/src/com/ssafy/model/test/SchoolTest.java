package com.ssafy.model.test;

import com.ssafy.model.service.SchoolService;
import com.ssafy.model.*;

public class SchoolTest{
    public static void main(String args[]) {
        SchoolService service = SchoolService.getInstance();
        service.add(new Student("S001", 18, "KYM", "Java Major"));
        service.add(new Student("S002", 18, "KTY", "Java Major"));        
        service.add(new Instructor("I001", 18, "KKW", "Java Major"));
        service.add(new Instructor("I002", 18, "KDH", "Java Major"));
        service.add(new Employee("E001", 18, "OJS", "Java Major"));
        service.add(new Employee("E002", 18, "KMS", "Java Major"));
        // service.add(new Person("P001", 16, "test"));
        Person[] persons = service.getPersons();
        for(Person p : persons)
            System.out.println(p);
    }
}

