package com.foundersc.pg.annotations;

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumn {

    // 列名称
    String msg();

    // 是否持久化，意味着是否与数据库字段对应
    boolean isPersistence() default true;

    // 定义是否是主键
    boolean isPrimaryKey() default false;

}
