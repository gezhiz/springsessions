
package com.worthto.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * 外部化配置作为依赖来源
 *
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
public class ExternalConfigurationDependencySourceDemo {

    @Autowired
    private String value;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            //只能通过类型方面的依赖注入和查找，而不能通过名称进行注入和查找。
            beanFactory.registerResolvableDependency(String.class, "HELLO WORLD !");

        });
        applicationContext.refresh();

        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);
        System.out.println(applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class).value);

        applicationContext.close();
    }
}
