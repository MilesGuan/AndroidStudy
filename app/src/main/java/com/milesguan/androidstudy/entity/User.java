package com.milesguan.androidstudy.entity;

/**
 *
 * 测试用model
 *
 */
public class User {

    private String name;

    private boolean isAdult;

    private School school;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setIsAdult(boolean isAdult) {
        this.isAdult = isAdult;
    }

    public boolean isMe(){
        return "Miles".equals(name);
    }




}
