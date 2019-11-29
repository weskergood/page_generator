package com.foundersc.pg.components;

import com.foundersc.pg.parsers.annotations.AnnotationParser;

/**
 * 表示一个普通文本框
 */
public class TextBox extends Component {

    public TextBox(String id, String elementId) {
        super(id, elementId);
    }

    @Override
    protected String generateHtml(AnnotationParser annotationParser) {
        return "<input class='easyui-textbox' style='width:150px'>";
    }
}
