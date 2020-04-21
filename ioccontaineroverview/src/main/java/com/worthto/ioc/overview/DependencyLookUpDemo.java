package com.worthto.ioc.overview;

import com.worthto.definition.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过名称查找
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class DependencyLookUpDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-look-context.xml");
        lookUpRealTime(beanFactory);
        lookUpLazy(beanFactory);
    }

    /**
     * 实时查找
     */
    private static void lookUpRealTime(BeanFactory beanFactory) {
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }

    /**
     * 延时查找
     */
    private static void lookUpLazy(BeanFactory beanFactory) {
        ObjectFactory person = (ObjectFactory) beanFactory.getBean("personFactory");
        System.out.println(person.getObject());
    }

}
