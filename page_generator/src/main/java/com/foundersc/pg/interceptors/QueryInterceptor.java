package com.foundersc.pg.interceptors;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.parsers.AnnotationParser;
import com.foundersc.pg.parsers.ColumnAnnotationParser;
import com.foundersc.pg.parsers.TableAnnotationParser;
import com.foundersc.pg.views.ColumnView;
import com.foundersc.pg.views.TableView;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 进入查询页面之前的拦截器
 */
public class QueryInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        List<ColumnView> tableColumns = null;
        String name = httpServletRequest.getParameter("id").toLowerCase();
        // 解析列信息
        AnnotationParser annotationParser = new ColumnAnnotationParser(PageGenerator.getData());
        Map<String, List<ColumnView>> parsedTableColumns = PageGenerator.getParsedTableColumns();
        if (!ObjectUtils.isEmpty(parsedTableColumns) || !parsedTableColumns.containsKey(name)) {
            parsedTableColumns = (Map<String, List<ColumnView>>) annotationParser.parse();
            for (Map.Entry<String, List<ColumnView>> next : parsedTableColumns.entrySet()) {
                if (next.getKey().equalsIgnoreCase(name)) {
                    tableColumns = next.getValue();
                    break;
                }
            }
        } else {
            tableColumns = parsedTableColumns.get(name);
        }
        session.setAttribute("tableColumns", tableColumns);
        session.setAttribute("eleId", name);
        // 最后解析表信息
        Map<String, TableView> parsedTables = PageGenerator.getParsedTables();
        if (!ObjectUtils.isEmpty(parsedTables) || !parsedTables.containsKey(name)) {
            AnnotationParser parser = new TableAnnotationParser(PageGenerator.getData());
            // 只要解析就可以
            parser.parse();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
