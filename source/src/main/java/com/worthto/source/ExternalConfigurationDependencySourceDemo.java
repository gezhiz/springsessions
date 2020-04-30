
package com.worthto.source;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 *
 * 外部化配置作为依赖来源
 *
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
@Configuration//导入外部化配置，必须要有这个注解
@PropertySource(value = "classpath:META-INF/default.properties", encoding = "utf-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${person.age:-1}")
    private String age;

    @Value("${person.name}")
    private String name;

    @Value("${person.resource:default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println(demo.age);
        System.out.println(demo.name);
        System.out.println(demo.resource);

        applicationContext.close();
    }
}
