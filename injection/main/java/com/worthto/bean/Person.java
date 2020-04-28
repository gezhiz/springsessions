package com.worthto.bean;

import com.worthto.enums.City;
import org.springframework.core.io.Resource;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class Person {
    private String name;
    private Integer age;
    /**
     * 枚举类型，会自动转换
     */
    private City city;

    /**
     * Resource 也会被转换
     */
    private Resource configFileLocation;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", configFileLocation=" + configFileLocation +
                '}';
    }

    public static Person createPerson() {
        Person person = new Person();
        person.setName("默认person");
        person.setAge(11);
        return person;
    }
}
