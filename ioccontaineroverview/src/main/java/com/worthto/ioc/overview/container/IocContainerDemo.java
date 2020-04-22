package com.worthto.ioc.overview.container;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author gezz
 * @description
 * @date 2020/4/22.
 */
public class IocContainerDemo {
    public static void main(String[] args) {
        //创建BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanCount = reader.loadBeanDefinitions("classpath:META-INF/dependency-look-context.xml");
        System.out.println("定义beanCount:" + beanCount);

    }
}
