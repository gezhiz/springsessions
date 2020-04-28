package com.worthto.injection.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 @see Aware 注入内建对象
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class AwareDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //以当前类作为配置类
        applicationContext.register(AwareDependencyInjectionDemo.class);

        applicationContext.refresh();

        //false
        System.out.println(AwareDependencyInjectionDemo.beanFactory == AwareDependencyInjectionDemo.applicationContext);
        //true
        System.out.println(AwareDependencyInjectionDemo.beanFactory == AwareDependencyInjectionDemo.applicationContext.getAutowireCapableBeanFactory());
        //true
        System.out.println(AwareDependencyInjectionDemo.beanFactory == applicationContext.getBeanFactory());
        //true
        System.out.println(applicationContext == AwareDependencyInjectionDemo.applicationContext);



        applicationContext.close();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareDependencyInjectionDemo.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareDependencyInjectionDemo.beanFactory = beanFactory;
    }
}
