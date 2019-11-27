package com.foundersc.pg.interceptors;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.parsers.TableColumnParser;
import com.foundersc.pg.views.ColumnView;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class QueryInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        List<ColumnView> tableColumns = null;
        String name = httpServletRequest.getParameter("id").toLowerCase();
        TableColumnParser annotationParser = new TableColumnParser(PageGenerator.getData());
        Map<String, List<ColumnView>> prepare = annotationParser.getListMap();
        if (!prepare.containsKey(name)){
            Map<String, List<ColumnView>> listMap = (Map<String, List<ColumnView>>) annotationParser.parse();
            for (Map.Entry<String, List<ColumnView>> next : listMap.entrySet()) {
                if (next.getKey().equalsIgnoreCase(name)){
                    tableColumns = next.getValue();
                    break;
                }
            }
        } else {
            tableColumns = prepare.get(name);
        }
        httpServletRequest.getSession().setAttribute("tableColumns", tableColumns);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
