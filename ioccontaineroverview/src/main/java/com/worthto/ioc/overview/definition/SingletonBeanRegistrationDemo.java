package com.worthto.ioc.overview.definition;

import com.worthto.ioc.overview.bean.PersonFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注册外部单体对象
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        PersonFactory personFactory = new PersonFactory();
        SingletonBeanRegistry singletonBeanRegistry = annotationConfigApplicationContext.getBeanFactory();
        singletonBeanRegistry.registerSingleton("personFactory", personFactory);
        annotationConfigApplicationContext.refresh();


        PersonFactory personFactory1 = annotationConfigApplicationContext.getBean("personFactory", PersonFactory.class);
        System.out.println(personFactory1 == personFactory);

    }
}
