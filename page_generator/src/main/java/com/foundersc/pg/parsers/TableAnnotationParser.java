package com.foundersc.pg.parsers;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.annotations.Table;
import com.foundersc.pg.views.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableAnnotationParser extends AnnotationParser {

    public TableAnnotationParser(List<Class<?>> data) {
        super(data);
    }

    @Override
    public Object parse() {
        List<Class<?>> data = this.data;
        Map<String, TableView> parsedTables = PageGenerator.getParsedTables();
        for (Class<?> c : data
        ) {
            if (c.isAnnotationPresent(Table.class)) {
                Table annotation = c.getAnnotation(Table.class);
                String tableName = annotation.tableName();
                TableView tableView = new TableView(tableName);
                parsedTables.put(c.getSimpleName().toLowerCase(), tableView);
            }
        }
        return parsedTables;
    }
}
