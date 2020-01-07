package de.yellowhing.sharedprefs.annotation;

import android.content.Context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SharedPrefs {
    String value() default "";
    int mode() default Context.MODE_PRIVATE;
}
