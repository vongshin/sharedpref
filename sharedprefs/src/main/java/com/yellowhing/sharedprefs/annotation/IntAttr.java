package com.yellowhing.sharedprefs.annotation;

import com.yellowhing.sharedprefs.Refine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntAttr {
    int value() default Refine.INT_DEF_VALUE;
    String key() default "";
}
