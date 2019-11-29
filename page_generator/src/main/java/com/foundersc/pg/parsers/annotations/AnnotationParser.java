package com.foundersc.pg.parsers.annotations;

import java.util.List;

/**
 * 注解解析器
 */
public abstract class AnnotationParser {

    List<Class<?>> data;

    AnnotationParser(List<Class<?>> data) {
        this.data = data;
    }

    public abstract Object parse();
}
