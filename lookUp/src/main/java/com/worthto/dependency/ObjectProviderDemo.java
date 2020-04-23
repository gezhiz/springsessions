package com.worthto.dependency;

import com.worthto.bean.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;
import java.util.function.Supplier;


/**
 * @author gezz
 * @description
 * @date 2020/4/23.
 */
public class ObjectProviderDemo {//@Configuration 是非必须的注解

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //以当前类作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动上下文
        applicationContext.refresh();

        lookUpByObjProvider(applicationContext);

        lookUpIfAvailable(applicationContext);


        lookUpByStream(applicationContext);


        applicationContext.close();

    }

    private static void lookUpByStream(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> stringsObjProvider = applicationContext.getBeanProvider(String.class);
//        for (String string : stringsObjProvider) {
//            System.out.println("string:" + string);
//        }
        stringsObjProvider.stream().forEach(s -> System.out.println("string:" + s));


    }

    private static void lookUpIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<Person> personObjectProvider = applicationContext.getBeanProvider(Person.class);
        Person person = personObjectProvider.getIfAvailable(() -> Person.createPerson());
        System.out.println(person);
    }

    @Bean
    @Primary
    public String helloWorld() {//bean名称和方法名称一致  helloWorld
        return "hello world!";
    }


    @Bean
    public String message() {//bean名称和方法名称一致  helloWorld
        return "Message";
    }

    /**
     * 延迟查找
     * @param applicationContext
     */
    private static void lookUpByObjProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> strObjectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(strObjectProvider.getObject());
    }
}
