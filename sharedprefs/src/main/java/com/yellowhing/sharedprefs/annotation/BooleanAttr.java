package com.yellowhing.sharedprefs.annotation;

import com.yellowhing.sharedprefs.Refine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface BooleanAttr {
    boolean value() default Refine.BOOLEAN_DEF_VALUE;
    String key() default "";
}
