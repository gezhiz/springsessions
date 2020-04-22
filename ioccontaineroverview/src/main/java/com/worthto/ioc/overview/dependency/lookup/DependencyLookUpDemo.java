package com.worthto.ioc.overview.dependency.lookup;

import com.worthto.ioc.overview.annotation.Super;
import com.worthto.ioc.overview.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class DependencyLookUpDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-look-context.xml");
//        lookUpRealTime(beanFactory);
//        lookUpLazy(beanFactory);
        lookUpByCollection(beanFactory);
//        lookUpByType(beanFactory);
        lookUpByAnnotation(beanFactory);
    }

    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Object> personMap = listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("注解对象：" + personMap);
        }

    }

    /**
     * 查找多个bean
     * @param beanFactory
     */
    private static void lookUpByCollection(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, Person> personMap = listableBeanFactory.getBeansOfType(Person.class);
            System.out.println("所有的person对象：" + personMap);
        }
    }

    /**
     * 实时查找
     */
    private static void lookUpByType(BeanFactory beanFactory) {
        Person person = beanFactory.getBean(Person.class);
        System.out.println("实时查找：" + person);
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
     * ObjectFactory 并没有产生新的Person对象
     */
    private static void lookUpLazy(BeanFactory beanFactory) {
        ObjectFactory person = (ObjectFactory) beanFactory.getBean("personFactory");
        System.out.println(person.getObject());
    }

}
