package com.foundersc.pg.annotations;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuItem {

    String title();
}
