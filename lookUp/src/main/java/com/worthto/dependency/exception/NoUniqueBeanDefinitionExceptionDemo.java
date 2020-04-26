package com.worthto.dependency.exception;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 演示
 * @see org.springframework.beans.factory.NoUniqueBeanDefinitionException
 *
 * @author gezz
 * @description
 * @date 2020/4/24.
 */
public class NoUniqueBeanDefinitionExceptionDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        applicationContext.refresh();

        String bean = applicationContext.getBean(String.class);

    }

    @Bean
    public String bean1() {
        return "bean1";
    }
    @Bean
    public String bean2() {
        return "bean2";
    }

}
