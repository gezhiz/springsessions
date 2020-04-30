package com.worthto.demo;

import com.worthto.bean.Person;
import com.worthto.scope.ThreadLocalScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
@Configuration
public class ThreadLocalScopeDemo {

    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public static Person person() {
        return createPerson();
    }

    public static Person createPerson() {
        Person person = new Person();
        person.setName("默认person");
        person.setAge(new Long(System.nanoTime()).intValue());
        return person;
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //注册自定义scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });

        applicationContext.refresh();
        ThreadLocalScopeDemo demo = applicationContext.getBean(ThreadLocalScopeDemo.class);

        new Thread(() -> System.out.println("线程"+ Thread.currentThread().getId() + " " + applicationContext.getBean(Person.class))).start();

        new Thread(() -> System.out.println("线程"+ Thread.currentThread().getId() + " " + applicationContext.getBean(Person.class))).start();

        System.out.println("线程"+ Thread.currentThread().getId() + " " + applicationContext.getBean(Person.class));
        System.out.println("线程"+ Thread.currentThread().getId() + " " + applicationContext.getBean(Person.class));
        Thread.sleep(10000);
        applicationContext.close();
    }
}
