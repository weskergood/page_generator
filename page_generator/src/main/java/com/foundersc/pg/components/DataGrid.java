package com.foundersc.pg.components;

import com.foundersc.pg.parsers.annotations.AnnotationParser;

/**
 * 表示一个数据表格
 */
public class DataGrid extends Component{

    public DataGrid(String id, String elementId) {
        super(id, elementId);
    }

    @Override
    protected String generateHtml(AnnotationParser annotationParser) {
        return null;
    }
}
