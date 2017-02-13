package com.milesguan.androidstudy.entity;

/**
 * Created by renjieguan on 16/11/2.
 */

public class Student {

    private int id;
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
