package com.foundersc.pg.parsers;

import com.foundersc.pg.annotations.TableColumn;
import com.foundersc.pg.views.ColumnView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableColumnParser extends AnnotationParser {

    public TableColumnParser(List<Class<?>> data) {
        super(data);
    }

    private Map<String, List<ColumnView>> listMap = new HashMap<>();

    public Map<String, List<ColumnView>> getListMap() {
        return listMap;
    }

    @Override
    public Object parse() {
        List<Class<?>> data = this.data;
        for (Class<?> c : data
        ) {
            Field[] declaredFields = c.getDeclaredFields();
            List<ColumnView> list = new ArrayList<>();
            for (Field field : declaredFields
            ) {
                if (field.isAnnotationPresent(TableColumn.class)) {
                    TableColumn annotation = field.getAnnotation(TableColumn.class);
                    String columnName = annotation.msg();
                    String filedName = field.getName();
                    list.add(new ColumnView(columnName, filedName));
                }
            }
            listMap.put(c.getSimpleName().toLowerCase(), list);
        }
        return listMap;
    }
}
