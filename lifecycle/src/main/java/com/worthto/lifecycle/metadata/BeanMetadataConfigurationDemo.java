package com.worthto.lifecycle.metadata;

import com.worthto.bean.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;

/**
 * 通过properties注册bean的元信息
 * @author gezz
 * @description
 * @date 2020/5/1.
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "classpath:/META-INF/person.properties";
        int count = beanDefinitionReader.loadBeanDefinitions(new EncodedResource( new ClassPathResource("/META-INF/person.properties"), "utf-8"));
        System.out.println("加载的beanDefinition 数量" + count);
        //可以通过
        Person person = beanFactory.getBean("person", Person.class);
        System.out.println(person);
    }
}
