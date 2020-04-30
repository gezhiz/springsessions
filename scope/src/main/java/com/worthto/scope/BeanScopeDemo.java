package com.worthto.scope;

import com.worthto.bean.Person;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * prototype 类型的对象，spring不会管理完整的bean的生命周期，一旦生成了对象，该scope bean对象就与spring容器脱离。
 * prototype对象 可以使用BeanPostProcessor 进行清理
 * Singleton 对象才会执行销毁@PreDestroy
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
//@Configuration
public class BeanScopeDemo implements DisposableBean{

    @Autowired
    @Qualifier("singletonPerson")
    private Person singletonPerson;

    @Autowired
    @Qualifier("prototypePerson")
    private Person prototypePerson;

    @Autowired
    @Qualifier("prototypePerson")
    private Person prototypePerson2;

    //集合类型，相同的beanName 只会存在一个名称对象
    @Autowired
    private Map<String,Person> personMap;
    //集合类型，相同的beanName 只会存在一个名称对象
    @Autowired
    private List<Person> personList;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;



    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public static Person singletonPerson() {
        Person person = new Person();
        person.setName("singleton");
        person.setAge(new Long(System.nanoTime()).intValue());
        return person;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static Person prototypePerson() {
        Person person = new Person();
        person.setName("scope");
        person.setAge(new Long(System.nanoTime()).intValue());
        return person;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanScopeDemo.class);
        applicationContext.refresh();
        BeanScopeDemo demo = applicationContext.getBean(BeanScopeDemo.class);
        lookUp(applicationContext);

        autowired(applicationContext);

        applicationContext.close();
    }

    /**
     * 依赖注入的方式
     * @param applicationContext
     */
    private static void autowired(AnnotationConfigApplicationContext applicationContext) {
        System.out.println("---------------autowired----------------");
        BeanScopeDemo demo =  applicationContext.getBean(BeanScopeDemo.class);
        System.out.println(demo.singletonPerson);
        System.out.println(demo.prototypePerson);
        System.out.println(demo.prototypePerson2);

        System.out.println(demo.personMap);
        System.out.println(demo.personList);

    }

    /**
     * 依赖查找的方式
     * @param applicationContext
     */
    private static void lookUp(AnnotationConfigApplicationContext applicationContext) {
        //依赖查找的作用于
        for (int i = 0; i < 4; i++) {
            Person prototypePerson = (Person) applicationContext.getBean("prototypePerson");
            //单例对象，每次都是同一个对象
            Person singletonPerson = (Person) applicationContext.getBean("singletonPerson");
            System.out.println(prototypePerson);
            System.out.println(singletonPerson);
        }
    }

    /**
     * 销毁prototype类型
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        this.singletonPerson.destory();
        this.prototypePerson.destory();
        this.prototypePerson2.destory();

        for (Map.Entry<String, Person> entries  : this.personMap.entrySet()) {
            BeanDefinition beanDefinition = this.beanFactory.getBeanDefinition(entries.getKey());
            if (beanDefinition.isPrototype()) {
                entries.getValue().destory();
            }
        }
    }
}
