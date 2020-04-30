package com.worthto.dependency.injection;

import com.worthto.bean.Person;
import com.worthto.dao.PersonDao;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入和依赖查找的 "依赖来源" 是不一样的
 * @author gezz
 * @description
 * @date 2020/4/21.
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");
        /**
         * 内建依赖
         */
        PersonDao personDao = beanFactory.getBean(PersonDao.class);
        //org.springframework.beans.factory.support.DefaultListableBeanFactory@6615435c:
        // defining beans [person,person2,superMan,personFactory,personDao]; root of factory hierarchy

        /**
         * （内建依赖）
         */
        //依赖注入了 BeanFactory 对象
        System.out.println(personDao.getBeanFactory());

        //下面报错
        //依赖查找
//        System.out.println(personDao.getBeanFactory().getBean(BeanFactory.class));


        ApplicationContext applicationContext = personDao.getApplicationContextObjectFactory().getObject();

        //true
        System.out.println(applicationContext == beanFactory);

        Person person = personDao.getPersonObjectFactory().getObject();
        System.out.println(person);

        /**
         * 容器内建bean
         */
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("内建bean对象 environment：" + environment);

        whoIsIocContainer(personDao, beanFactory);

        System.out.println(personDao.getFactoryCreatedPerson());
    }


    /**
     * ApplicationContext 是BeanFactory的扩展，支持了资源加载等功能
     *
     * @param personDao
     * @param beanFactory
     */
    private static void whoIsIocContainer(PersonDao personDao, BeanFactory beanFactory) {
        ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext) beanFactory;

        BeanFactory beanFactoryInApplicationContext = applicationContext.getBeanFactory();

        //false
        System.out.println(personDao.getBeanFactory() == beanFactory);

        //true
        System.out.println(personDao.getBeanFactory() == beanFactoryInApplicationContext);

        //ApplicationContext 就是BeanFactory
        //在AbstractRefreshableApplicationContext 的实现中，
        // 使用装饰设计模式，组合了一个BeanFactory对象，这个对象作为真正的ioc容器，为ApplicationContext提供相关的服务
    }

}
