package com.worthto.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.*;

/**
 * 自定义 依赖注入注解
 * @author gezz
 * @description
 * @date 2020/4/29.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
public @interface MyAutowired {
}
