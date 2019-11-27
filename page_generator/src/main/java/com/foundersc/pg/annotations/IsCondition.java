package com.foundersc.pg.annotations;

import com.foundersc.pg.utils.QueryType;

import java.lang.annotation.*;

@Target(value = {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IsCondition {

    // 指定条件查询类型
    QueryType queryType();
}
