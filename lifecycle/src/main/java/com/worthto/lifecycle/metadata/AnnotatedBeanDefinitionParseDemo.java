package com.worthto.lifecycle.metadata;

import com.worthto.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Bean;

/**
 * 通过AnnotatedBeanDefinitionReader注册bean的元信息
 * @author gezz
 * @description
 * @date 2020/5/1.
 */
//@Configuration
public class AnnotatedBeanDefinitionParseDemo {

    @Autowired
    private Person person;


    @Bean
    private Person person() {
        return Person.createPerson();
    }

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //基于java注解的BeanDefinitionReader
        AnnotatedBeanDefinitionReader definitionReader = new AnnotatedBeanDefinitionReader(beanFactory);
        //注册当前类（当前了是个普通类 非 Component类，）
        definitionReader.register(AnnotatedBeanDefinitionParseDemo.class);
        //配置bean名称生成器,默认
        definitionReader.setBeanNameGenerator(new AnnotationBeanNameGenerator());

        //@see AnnotationBeanNameGenerator 生成的beanName
        AnnotatedBeanDefinitionParseDemo demo = beanFactory.getBean(AnnotatedBeanDefinitionParseDemo.class);
        AnnotatedBeanDefinitionParseDemo demo1 = (AnnotatedBeanDefinitionParseDemo) beanFactory.getBean("annotatedBeanDefinitionParseDemo");
        System.out.println(demo);
        System.out.println(demo1);
        // null
        System.out.println(demo.person);


    }
}
