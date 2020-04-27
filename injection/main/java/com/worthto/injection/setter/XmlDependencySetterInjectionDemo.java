package com.worthto.injection.setter;

import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class XmlDependencySetterInjectionDemo {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(beanFactory);
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-injection-context.xml");

        PersonHolder personHolder = beanFactory.getBean(PersonHolder.class);
        System.out.println(personHolder);

    }

}
