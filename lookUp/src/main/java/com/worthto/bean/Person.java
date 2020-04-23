package com.worthto.bean;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class Person {
    private String name;
    private Integer age;

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static Person createPerson() {
        Person person = new Person();
        person.setName("默认person");
        person.setAge(11);
        return person;
    }
}
