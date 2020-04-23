package com.worthto.dependency;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigUtils;

/**
 * 内建依赖查找
 * @author gezz
 * @description
 * @date 2020/4/23.
 */
public class InternalDependencyLookUp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.refresh();

        //内建依赖的查找
        AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor = (AutowiredAnnotationBeanPostProcessor) applicationContext.getBean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME);

        System.out.println(annotationBeanPostProcessor);
    }
}
