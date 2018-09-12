package com.zzy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ss
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ScActionAnnotation {
    String value();
}
