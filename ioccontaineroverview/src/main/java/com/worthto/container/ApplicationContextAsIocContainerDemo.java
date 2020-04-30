package com.worthto.container;

import com.worthto.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * @author gezz
 * @description
 * @date 2020/4/22.
 */
public class ApplicationContextAsIocContainerDemo {
    public static void main(String[] args) {
        String location = "classpath:META-INF/dependency-look-context.xml";
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //以当前类作为配置类
        applicationContext.register(ApplicationContextAsIocContainerDemo.class);
        //启动上下文
        applicationContext.refresh();

        lookUpByCollection(applicationContext);
        applicationContext.close();
    }

    @Bean
    public Person person() {
        Person person = new Person();
        person.setAge(12);
        person.setName("annotation bean");
        return person;
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
