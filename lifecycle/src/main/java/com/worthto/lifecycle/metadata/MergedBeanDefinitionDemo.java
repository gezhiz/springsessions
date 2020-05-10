package com.worthto.lifecycle.metadata;

import com.worthto.bean.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @author gezz
 * @description
 * @date 2020/5/1.
 */
public class MergedBeanDefinitionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanReader = new XmlBeanDefinitionReader(beanFactory);

        int count = beanReader.loadBeanDefinitions(new EncodedResource( new ClassPathResource("META-INF/dependency-look-context.xml"), "utf-8"));
        Person person = (Person) beanFactory.getBean("person");
        Person superMan = (Person) beanFactory.getBean("superMan");
        BeanDefinition beanDefinition = beanFactory.getMergedBeanDefinition("superMan");

        System.out.println(person);
        System.out.println(superMan);

    }
}
