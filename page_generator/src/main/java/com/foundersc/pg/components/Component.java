package com.foundersc.pg.components;

import com.foundersc.pg.parsers.annotations.AnnotationParser;

public abstract class Component {

    protected String id;

    protected String elementId;

    public Component(String id, String elementId) {
        this.id = id;
        this.elementId = elementId;
    }

    protected abstract String generateHtml(AnnotationParser annotationParser);
}
