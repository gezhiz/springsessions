package com.worthto.ioc.overview.bean;

/**
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
public class PersonFactory {

    public Person createPerson() {
        return new Person();
    }
}
