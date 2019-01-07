package com.tensquare.base.pojo;

public class People {
    private String name;
    private Integer age;

    public Man getMan() {
        return man;
    }

    public void setMan(Man man) {
        this.man = man;
    }

    private Man man;


    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public People(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
