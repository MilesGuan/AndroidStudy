package com.milesguan.androidstudy.entity;

import java.util.LinkedList;
import java.util.List;

public class School {

    private String name;

    private List<Student> students;

    public School(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public static List<School> getTestSchool(){
        List<School> schools = new LinkedList<>();

        List<Student> students1 = new LinkedList<>();
        students1.add(new Student("Li"));
        students1.add(new Student("Wang"));
        School school1 = new School("a");
        school1.setStudents(students1);
        schools.add(school1);


        List<Student> students2 = new LinkedList<>();
        students2.add(new Student("Eva"));
        students2.add(new Student("Peter"));
        School school2 = new School("b");
        school1.setStudents(students2);
        schools.add(school2);

        return schools;
    }



}
