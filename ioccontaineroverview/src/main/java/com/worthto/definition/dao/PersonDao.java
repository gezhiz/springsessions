package com.worthto.definition.dao;

import com.worthto.definition.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @author gezz
 * @description
 * @date 2020/4/22.
 */

public class PersonDao {

    private Collection<Person> persons;//自定义的bean

    private BeanFactory beanFactory;//内建 的非bean对象   内建依赖

    private ObjectFactory<Person> personObjectFactory;

    private ObjectFactory<ApplicationContext> applicationContextObjectFactory;

    public Collection<Person> getPersons() {
        return persons;
    }

    public void setPersons(Collection<Person> persons) {
        this.persons = persons;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<Person> getPersonObjectFactory() {
        return personObjectFactory;
    }

    public void setPersonObjectFactory(ObjectFactory<Person> personObjectFactory) {
        this.personObjectFactory = personObjectFactory;
    }

    public ObjectFactory<ApplicationContext> getApplicationContextObjectFactory() {
        return applicationContextObjectFactory;
    }

    public void setApplicationContextObjectFactory(ObjectFactory<ApplicationContext> applicationContextObjectFactory) {
        this.applicationContextObjectFactory = applicationContextObjectFactory;
    }
}
