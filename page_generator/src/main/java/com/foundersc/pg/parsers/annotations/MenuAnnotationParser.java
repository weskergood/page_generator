package com.foundersc.pg.parsers.annotations;

import com.foundersc.pg.annotations.MenuItem;
import com.foundersc.pg.views.MenuView;

import java.util.ArrayList;
import java.util.List;

public class MenuAnnotationParser extends AnnotationParser {

    public MenuAnnotationParser(List<Class<?>> data) {
        super(data);
    }

    @Override
    public Object parse() {
        List<MenuView> menuViews = new ArrayList<>();
        List<Class<?>> data = this.data;
        for (Class<?> c : data
        ) {
            if (c.isAnnotationPresent(MenuItem.class)) {
                MenuItem annotation = c.getAnnotation(MenuItem.class);
                MenuView menuView = new MenuView(annotation.title(), c.getSimpleName().toLowerCase());
                menuViews.add(menuView);
            }
        }
        return menuViews;
    }
}
