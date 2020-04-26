package com.worthto.injection;

import com.worthto.bean.PersonHolder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过api的方式，注入依赖
 * @author gezz
 * @description
 * @date 2020/4/26.
 */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册PersonHolder 的BeanDefinition

        applicationContext.registerBeanDefinition("personHolder",  createPersonHolderBeanDefinition());


        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(applicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-api-injection-context.xml");

        applicationContext.refresh();

        PersonHolder personHolder = applicationContext.getBean(PersonHolder.class);
        System.out.println(personHolder);

    }

    /**
     * spring底层也是通过这种方式添加的注入
     * @return
     */
    private static BeanDefinition createPersonHolderBeanDefinition() {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(PersonHolder.class);
        builder.addPropertyReference("person", "superMan");
        return builder.getBeanDefinition();
    }
//
//    @Bean
//    public PersonHolder personHolder(Person person) {
//        return new PersonHolder(person);
//    }


}
