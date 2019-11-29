package com.foundersc.pg.interceptors;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.parsers.annotations.AnnotationParser;
import com.foundersc.pg.parsers.annotations.ColumnAnnotationParser;
import com.foundersc.pg.parsers.annotations.TableAnnotationParser;
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
 * 查询拦截器，当进入到查询模板前拦截请求，对查询模板进行一定操作
 */
public class QueryInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        List<ColumnView> tableColumns = null;
        String name = httpServletRequest.getParameter("id").toLowerCase();
        // 使用列注解解析器，解析基本信息中的列信息
        AnnotationParser annotationParser = new ColumnAnnotationParser(PageGenerator.getData());
        Map<String, List<ColumnView>> parsedTableColumns = PageGenerator.getParsedTableColumns();
        // 由于将解析后的列注解信息全部缓存到一个map中，所以只有第一次进入到查询拦截器，进行整体解析
        // ，后续会直接冲map中获取
        if (!ObjectUtils.isEmpty(parsedTableColumns) || !parsedTableColumns.containsKey(name)) {
            parsedTableColumns = (Map<String, List<ColumnView>>) annotationParser.parse();
            for (Map.Entry<String, List<ColumnView>> next : parsedTableColumns.entrySet()) {
                // name为小写简单类名，如:user
                if (next.getKey().equalsIgnoreCase(name)) {
                    tableColumns = next.getValue();
                    break;
                }
            }
        } else {
            // 若缓存中存在，则直接取出，不需要解析
            tableColumns = parsedTableColumns.get(name);
        }
        // 将列名放入到session中，供前端显示列名
        session.setAttribute("tableColumns", tableColumns);
        // 用于标识此查询是具体哪个类(哪张表)的查询
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
