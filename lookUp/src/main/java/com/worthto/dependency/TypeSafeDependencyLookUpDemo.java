package com.worthto.dependency;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 类型安全的依赖查找
 * @author gezz
 * @description
 * @date 2020/4/23.
 */
public class TypeSafeDependencyLookUpDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //以当前类作为配置类
        applicationContext.register(ObjectProviderDemo.class);
        //启动上下文
        applicationContext.refresh();

        displayUnSafe(applicationContext);

        displayUnsafeObjectFactory(applicationContext);

        lookUpIfAvailableSafe(applicationContext);

        applicationContext.close();

    }

    private static void displayUnsafeObjectFactory(BeanFactory beanFactory) {
        ObjectFactory<TypeSafeDependencyLookUpDemo> objectFactory = beanFactory.getBeanProvider(TypeSafeDependencyLookUpDemo.class);
        printException(() -> objectFactory.getObject());
    }

    /**
     * 查找bean时，bean不存在或者其他原因导致抛出异常(其他异常：比如查询一个，却存在多个时报错)b，成为不安全的依赖查找
     * @param beanFactory
     */
    private static void displayUnSafe(BeanFactory beanFactory) {
        try {
            beanFactory.getBean(TypeSafeDependencyLookUpDemo.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }

    //不存在也不报错，安全查找
    private static void lookUpIfAvailableSafe(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<TypeSafeDependencyLookUpDemo> personObjectProvider = applicationContext.getBeanProvider(TypeSafeDependencyLookUpDemo.class);
        printException(() -> personObjectProvider.getIfAvailable());
    }

    /**
     * 借助lambda表达式打印异常
     */
    private static void printException(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
