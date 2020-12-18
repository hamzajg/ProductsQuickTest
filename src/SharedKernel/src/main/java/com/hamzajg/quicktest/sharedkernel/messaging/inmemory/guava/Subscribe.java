package com.hamzajg.quicktest.sharedkernel.messaging.inmemory.guava;

import java.lang.annotation.*;

@Repeatable(Subscribe.List.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

    String value() default "";

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface List {

        Subscribe[] value();
    }
}