package com.foundersc.pg.parsers.annotations;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.annotations.TableColumn;
import com.foundersc.pg.views.ColumnView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ColumnAnnotationParser extends AnnotationParser {

    public ColumnAnnotationParser(List<Class<?>> data) {
        super(data);
    }

    @Override
    public Object parse() {
        List<Class<?>> data = this.data;
        Map<String, List<ColumnView>> parsedTableColumns = PageGenerator.getParsedTableColumns();
        for (Class<?> c : data
        ) {
            Field[] declaredFields = c.getDeclaredFields();
            List<ColumnView> list = new ArrayList<>();
            for (Field field : declaredFields
            ) {
                if (field.isAnnotationPresent(TableColumn.class)) {
                    TableColumn annotation = field.getAnnotation(TableColumn.class);
                    // 列只显示数据库中的字段
                    if (annotation.isPersistence()) {
                        String columnName = annotation.msg();
                        String filedName = field.getName();
                        list.add(new ColumnView(columnName, filedName, annotation.isPrimaryKey()));
                    }
                }
            }
            if (list.size() > 0){
                parsedTableColumns.put(c.getSimpleName().toLowerCase(), list);
            }
        }
        return parsedTableColumns;
    }
}
