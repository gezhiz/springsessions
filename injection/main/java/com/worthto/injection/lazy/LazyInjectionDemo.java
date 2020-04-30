package com.worthto.injection.lazy;

import com.worthto.bean.Person;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author gezz
 * @description
 * @date 2020/4/29.
 */
@Configuration
public class LazyInjectionDemo {

    @Autowired
    private ObjectProvider<Person> personObjectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(LazyInjectionDemo.class);


        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        annotationConfigApplicationContext.refresh();
        LazyInjectionDemo lazyInjectionDemo = annotationConfigApplicationContext.getBean(LazyInjectionDemo.class);


        System.out.println(lazyInjectionDemo.personObjectProvider.getObject());
        //通过延迟加载获取所有的Person对象
        lazyInjectionDemo.personObjectProvider.forEach(person -> {
            System.out.println(person);
        });
    }

    public ObjectProvider<Person> getPersonObjectProvider() {
        return personObjectProvider;
    }

    public void setPersonObjectProvider(ObjectProvider<Person> personObjectProvider) {
        this.personObjectProvider = personObjectProvider;
    }
}
