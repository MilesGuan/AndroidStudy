package com.milesguan.androidstudy.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.LinkedList;
import java.util.List;

public class School implements Parcelable {

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
        school2.setStudents(students2);
        schools.add(school2);

        return schools;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.students);
    }

    protected School(Parcel in) {
        this.name = in.readString();
        this.students = in.createTypedArrayList(Student.CREATOR);
    }

    public static final Parcelable.Creator<School> CREATOR = new Parcelable.Creator<School>() {
        @Override
        public School createFromParcel(Parcel source) {
            return new School(source);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };
}
