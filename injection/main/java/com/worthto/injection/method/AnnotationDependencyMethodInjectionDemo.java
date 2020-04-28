package com.worthto.injection.method;

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
public class AnnotationDependencyMethodInjectionDemo {

    private PersonHolder personHolder;

    private PersonHolder personHolder2;

    @Bean
    public PersonHolder personHolder(Person person) {
        return new PersonHolder(person);
    }

    @Autowired
    public void initPersonHolder(PersonHolder personHolder) {
        this.personHolder = personHolder;
    }

    @Autowired
    public void initPersonHolder2(PersonHolder personHolder2) {
        this.personHolder2 = personHolder2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //以当前类作为配置类
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(applicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        //依赖查找
        AnnotationDependencyMethodInjectionDemo fieldInjectionDemo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        //依赖注入
        System.out.println(fieldInjectionDemo.personHolder);
        System.out.println(fieldInjectionDemo.personHolder2);
        applicationContext.close();

    }
}
