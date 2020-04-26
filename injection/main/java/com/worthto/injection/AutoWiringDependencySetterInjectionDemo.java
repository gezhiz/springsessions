package com.worthto.injection;

import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class AutoWiringDependencySetterInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(beanFactory);
        beanReader.loadBeanDefinitions("classpath:/META-INF/autowiring-dependency-injection-context.xml");

        PersonHolder personHolder = beanFactory.getBean(PersonHolder.class);
        System.out.println(personHolder);

    }

}
