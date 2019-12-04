package de.yellowhing.sharedprefs.annotation;

import de.yellowhing.sharedprefs.Refine;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StringAttr {
    String value() default Refine.STRING_DEF_VALUE;
    String key() default "";
}
