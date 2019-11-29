package com.foundersc.pg.components;

import com.foundersc.pg.parsers.annotations.AnnotationParser;

/**
 * 将封装多个页面组件信息，例如：文本框、密码框、下拉菜单等
 */
public abstract class Component {

    protected String id;

    protected String elementId;

    public Component(String id, String elementId) {
        this.id = id;
        this.elementId = elementId;
    }

    /**
     * 生成对应的html代码
     * @param annotationParser
     * @return
     */
    protected abstract String generateHtml(AnnotationParser annotationParser);
}
