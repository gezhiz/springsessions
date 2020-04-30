package com.worthto.injection.qualifier;

import com.worthto.annotation.PersonGroup;
import com.worthto.bean.Person;
import com.worthto.enums.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Qualifier 注解的依赖注入
 * @author gezz
 * @description
 * @date 2020/4/29.
 */
public class QualifierInjectionDemo {

    @Autowired
    @Qualifier("person")//通过@Qualifier指定bean的名称
    private Person person;

    @Autowired//获取primary=true的那个对象
    private Person person2;


    @Autowired//所有的person对象都会进入这个集合
    private List<Person> personList;

    @Autowired
    @Qualifier//只会添加含有Qualifier注解的person对象 同时也会加入所有的继承了Qualifier注解的person对象
    private List<Person> qualifierList;

    @Autowired
    @PersonGroup//只会添加含有Qualifier注解的person对象
    private List<Person> groupPersons;

    @Bean
    @Qualifier
    public Person person3() {
        Person person3 = new Person();
        person3.setAge(12312);
        person3.setName("person3");
        person3.setCity(City.BEI_JING);
        return person3;
    }

    @Bean
    @Qualifier
    public Person person4() {
        Person person4 = new Person();
        person4.setAge(112);
        person4.setName("person4");
        person4.setCity(City.BEI_JING);
        return person4;
    }

    @Bean
    @PersonGroup
    public Person groupPerson1() {
        Person groupPerson1 = new Person();
        groupPerson1.setAge(12312);
        groupPerson1.setName("groupPerson1");
        groupPerson1.setCity(City.BEI_JING);
        return groupPerson1;
    }
    @Bean
    @PersonGroup
    public Person groupPerson2() {
        Person groupPerson2 = new Person();
        groupPerson2.setAge(12312);
        groupPerson2.setName("groupPerson2");
        groupPerson2.setCity(City.BEI_JING);
        return groupPerson2;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(QualifierInjectionDemo.class);


        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        annotationConfigApplicationContext.refresh();
        QualifierInjectionDemo qualifierInjectionDemo = annotationConfigApplicationContext.getBean(QualifierInjectionDemo.class);
        System.out.println(qualifierInjectionDemo.person);
        System.out.println(qualifierInjectionDemo.person2);

        System.out.println(qualifierInjectionDemo.personList);

        System.out.println(qualifierInjectionDemo.qualifierList);

        System.out.println(qualifierInjectionDemo.groupPersons);
    }


}
