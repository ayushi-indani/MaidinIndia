package com.example.ayushi.madeinindia;

/**
 * Created by Ayushi on 5/7/2017.
 */

public class Worker {
    String name;
    String mobile;
    String age;
    String city;
    String area;
    String gender;
    String religion;
    String hours;
    String salary;

    public Worker() {
    }

    public Worker(String name, String mobile, String age, String city, String area, String gender, String religion, String hours, String salary) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
        this.city = city;
        this.area = area;
        this.gender = gender;
        this.religion = religion;
        this.hours = hours;
        this.salary = salary;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}