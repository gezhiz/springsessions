package com.worthto.ioc.overview;

import com.worthto.definition.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class DependencyLookUpDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-look-context.xml");
        Person person = beanFactory.getBean(Person.class);
        System.out.println(person);
    }

}
