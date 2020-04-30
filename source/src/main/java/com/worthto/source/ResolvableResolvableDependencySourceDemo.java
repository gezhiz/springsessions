package com.worthto.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * ResolvableDependency 注入非spring管理的对象
 *
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
public class ResolvableResolvableDependencySourceDemo {

    @Autowired
    private String value;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ResolvableResolvableDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //只能通过类型方面的依赖注入和查找，而不能通过名称进行注入和查找。
            beanFactory.registerResolvableDependency(String.class, "HELLO WORLD !");

        });
        applicationContext.refresh();

        ResolvableResolvableDependencySourceDemo demo = applicationContext.getBean(ResolvableResolvableDependencySourceDemo.class);
        System.out.println(applicationContext.getBean(ResolvableResolvableDependencySourceDemo.class).value);

        applicationContext.close();
    }
}
