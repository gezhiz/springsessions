package com.worthto.injection.resolution;

import com.worthto.annotation.InjectUser;
import com.worthto.annotation.MyAutowired;
import com.worthto.bean.Person;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;

import static org.springframework.context.annotation.AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME;

/**
 *
 * 注解驱动的依赖处理过程
 * 跟踪进入DefaultListableBeanFactory.resolveDependency()方法进行分析
 *
 * @see org.springframework.context.annotation.AnnotationConfigUtils  这个方法会添加很多注解解释器
 * @author gezz
 * @description
 * @date 2020/4/29.
 */
@Configuration
public class AnnotationDIResolutionDemo {

    @Autowired
    @Lazy//lazy=true
    private Person lazyPerson;//spring返回了一个cglib的代理对象

    @MyAutowired
    private Person person;
    // DependencyDescriptor ----->>> (required=true  eager=true primary=true)
    // 实时注入 + 通过类依赖查找（Person.class）+ 字段名称（"person"）

    @Autowired//key表示名称 Person具体的对象
    private Map<String,Person> personMap;

//    @Autowired
    @InjectUser//@since java8 primary作为首选
    private Optional<Person> optional;

    @Autowired
    private BeanFactory beanFactory;

//    @Bean
//    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        //setAutowiredAnnotationType 会替换内部默认的注解类型
//        annotationBeanPostProcessor.setAutowiredAnnotationType(MyAutowired.class);
//        return annotationBeanPostProcessor;
//    }

    //AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME 覆盖了默认AutowiredAnnotationProcessorBeannName的实现
    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    private static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor annotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        annotationBeanPostProcessor.setAutowiredAnnotationTypes(new LinkedHashSet<>(Arrays.asList(Autowired.class,InjectUser.class)));
        return annotationBeanPostProcessor;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AnnotationDIResolutionDemo.class);


        BeanDefinitionReader beanReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        //同样可以使用xml作为依赖的来源
        beanReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        annotationConfigApplicationContext.refresh();
        AnnotationDIResolutionDemo diResolutionDemo = annotationConfigApplicationContext.getBean(AnnotationDIResolutionDemo.class);
        System.out.println(diResolutionDemo.person);
        System.out.println(diResolutionDemo.personMap);
        System.out.println(diResolutionDemo.optional.get());

        annotationConfigApplicationContext.close();
    }
}
