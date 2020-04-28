package com.worthto.injection.constructor;

import com.worthto.bean.Person;
import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过Constructor java 代码的方式 注入
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AnnotationDependencyConstructorInjectionDemo.class);


        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        annotationConfigApplicationContext.refresh();

        PersonHolder personHolder = annotationConfigApplicationContext.getBean(PersonHolder.class);
        System.out.println(personHolder);
    }

    @Bean
    public PersonHolder personHolder(Person superMan) {
        return new PersonHolder(superMan);
    }


}
