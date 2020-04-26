package com.worthto.dependency.exception;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 演示
 * @see org.springframework.beans.BeanInstantiationException
 *
 * @author gezz
 * @description
 * @date 2020/4/24.
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInstantiationExceptionDemo.class);

        /**
         * 注册 BeandefinitionBuilder 是一个CharSequence接口  接口无法进行初始化，只有类才能进行这样注册，所以会报错
         * @see org.springframework.beans.BeanInstantiationException
         *
         * Caused by: org.springframework.beans.BeanInstantiationException:
         *      Failed to instantiate [java.lang.CharSequence]: Specified class is an interface
         */
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);

        //注册一个bean
        applicationContext.registerBeanDefinition("errorBean", builder.getBeanDefinition());

        applicationContext.refresh();

    }

}
