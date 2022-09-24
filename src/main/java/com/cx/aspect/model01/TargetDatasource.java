package com.cx.aspect.model01;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
//@Reference
//@Inherited
public @interface TargetDatasource {
    @AliasFor(attribute = "value")
    String database() default "";
    @AliasFor(attribute = "database")
    String value() default "";
}
