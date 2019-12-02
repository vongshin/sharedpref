package com.yellowhing.sharedprefs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StringSetAttr {
    String[] value() default {};
    String key() default "";
}
