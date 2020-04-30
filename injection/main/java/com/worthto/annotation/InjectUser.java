package com.worthto.annotation;

import java.lang.annotation.*;

/**
 * @author gezz
 * @description
 * @date 2020/4/29.
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectUser {
}
