package com.worthto.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源
 * 依赖注入和依赖查找的 依赖来源 不同
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
public class DependencySourceDemo {

    /**
     * @see org.springframework.context.support.AbstractApplicationContext.prepareBeanFactory()
     * 下面是依赖管理对象 Resolveable Dependency
     */
    //注入在postProcessProperties方法执行,早于setter注入和@PostConctruct
    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("init --------- start");
        //true
        System.out.println(beanFactory == applicationContext.getAutowireCapableBeanFactory());
        //true
        System.out.println(resourceLoader == applicationContext);
        //true
        System.out.println(applicationEventPublisher == applicationContext);
        //true
        System.out.println(resourceLoader == applicationContext);
        System.out.println("init --------- end");
    }

    @PostConstruct
    public void getBean() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }
    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("依赖查找找不到对应的类：" + beanType);
            return null;
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);
        applicationContext.refresh();
        DependencySourceDemo demo = applicationContext.getBean(DependencySourceDemo.class);

        applicationContext.close();
    }
}
