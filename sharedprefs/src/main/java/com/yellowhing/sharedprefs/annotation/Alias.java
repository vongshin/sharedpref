package com.yellowhing.sharedprefs.annotation;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Alias {
    String value() default "";
}
