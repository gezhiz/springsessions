package com.worthto.injection.field;

import com.worthto.bean.Person;
import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 字段注入的方式
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class AnnotationDependencyFieldInjectionDemo {

    // @see AutowiredAnnotationBeanPostProcessor
    @Autowired
    private PersonHolder personHolder;

    //Autowired会忽略static字段,person字段为nul
    @Resource(name = "person")
    private Person person;

    @Resource
    private PersonHolder personHolder2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //以当前类作为配置类
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(applicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        //依赖查找
        AnnotationDependencyFieldInjectionDemo fieldInjectionDemo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);
        //依赖注入
        System.out.println(fieldInjectionDemo.personHolder);
        System.out.println(fieldInjectionDemo.personHolder2);

        System.out.println(fieldInjectionDemo.person);


    }

    @Bean
    public PersonHolder personHolder(Person person) {
        return new PersonHolder(person);
    }


}
