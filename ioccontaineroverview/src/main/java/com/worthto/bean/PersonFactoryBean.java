package com.worthto.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author gezz
 * @description
 * @date 2020/4/22.
 */
@Component(value = "factoryCreatedPerson")
public class PersonFactoryBean implements FactoryBean<Person> {

    @Override
    public Person getObject() throws Exception {
        Person person = new Person();
        person.setName("factory created");
        person.setAge(123);
        return person;
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
