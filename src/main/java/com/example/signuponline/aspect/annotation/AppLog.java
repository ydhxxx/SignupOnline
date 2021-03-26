package com.example.signuponline.aspect.annotation;

import java.lang.annotation.*;

/**
 * ...
 *
 * @author yudh
 * @date 2020-12-23 15:51:48
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface AppLog {
    /**
     * 日志说明
     *
     * @return
     */
    String value() default "";
}
