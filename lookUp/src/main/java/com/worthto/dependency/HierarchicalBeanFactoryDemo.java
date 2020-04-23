package com.worthto.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找
 * @author gezz
 * @description
 * @date 2020/4/23.
 */
public class HierarchicalBeanFactoryDemo {//@Configuration 是非必须的注解

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        ConfigurableBeanFactory configurableBeanFactory = applicationContext.getBeanFactory();
        System.out.println("当前beanFactory parent:" + configurableBeanFactory.getParentBeanFactory());

        ConfigurableBeanFactory parentBeanFactory = createBeanFactory();
        configurableBeanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前beanFactory parent:" + configurableBeanFactory.getParentBeanFactory());

        displayLocalBean(configurableBeanFactory, "person");
        displayLocalBean(parentBeanFactory, "person");

        displayContainsBean(parentBeanFactory, "person");
        displayContainsBean(configurableBeanFactory, "person");

        //以当前类作为配置类
        applicationContext.register(HierarchicalBeanFactoryDemo.class);
        //启动上下文
        applicationContext.refresh();

        applicationContext.close();

    }

    /**
     * 是否包含local bean
     * @param hierarchicalBeanFactory
     * @param beanName
     */
    private static void displayContainsBean(HierarchicalBeanFactory hierarchicalBeanFactory, String beanName) {
        System.out.printf("当前beanFactory [%s] \n包含的local bean : [name %s]: %s\n"
                , hierarchicalBeanFactory, beanName, containsBean(hierarchicalBeanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        //委派给父容器查找
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);

    }

    /**
     * 是否包含local bean
     * @param hierarchicalBeanFactory
     * @param name
     */
    private static void displayLocalBean(HierarchicalBeanFactory hierarchicalBeanFactory, String name) {
        System.out.printf("当前beanFactory [%s] \n包含的local bean : [name %s]: %s\n"
                , hierarchicalBeanFactory, name, hierarchicalBeanFactory.containsLocalBean(name));
    }

    private static ConfigurableBeanFactory createBeanFactory() {
        //创建BeanFactory 容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        int beanCount = reader.loadBeanDefinitions("classpath:META-INF/dependency-look-context.xml");
        return beanFactory;
    }
}
