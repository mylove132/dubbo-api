package com.dubbo.api.aop;

import java.lang.annotation.*;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:14:42
 * Modify date: 2019-06-28:14:42
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthPermission {

    String[] roles() default "";
}
