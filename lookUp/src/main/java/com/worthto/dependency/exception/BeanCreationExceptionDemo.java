package com.worthto.dependency.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * @see org.springframework.beans.factory.BeanCreationException
 * @author gezz
 * @description
 * @date 2020/4/24.
 */
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInstantiationExceptionDemo.class);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Pojo.class);

        /**
         * Exception in thread "main" org.springframework.beans.factory.BeanCreationException:
         * Error creating bean with name 'pojo': Invocation of init method failed;
         * nested exception is java.lang.Exception
         */
        applicationContext.registerBeanDefinition("pojo", builder.getBeanDefinition());
        applicationContext.refresh();

    }

    @Service
    static class Pojo implements InitializingBean {

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception();
        }
    }
}
