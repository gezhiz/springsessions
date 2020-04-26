package com.worthto.injection;

import com.worthto.bean.Person;
import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //以当前类作为配置类
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(applicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-injection-context.xml");

        applicationContext.refresh();

        PersonHolder personHolder = applicationContext.getBean(PersonHolder.class);
        System.out.println(personHolder);

    }

    @Bean
    public PersonHolder personHolder(Person person) {
        return new PersonHolder(person);
    }


}
