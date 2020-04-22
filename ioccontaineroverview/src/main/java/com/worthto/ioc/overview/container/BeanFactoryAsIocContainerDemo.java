package com.worthto.ioc.overview.container;

import com.worthto.ioc.overview.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author gezz
 * @description
 * @date 2020/4/22.
 */
public class BeanFactoryAsIocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanCount = reader.loadBeanDefinitions("classpath:META-INF/dependency-look-context.xml");
        System.out.println("定义beanCount:" + beanCount);

        lookUpByCollection(beanFactory);
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
}
