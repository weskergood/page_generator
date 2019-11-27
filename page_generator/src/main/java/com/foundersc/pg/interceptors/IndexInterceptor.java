package com.foundersc.pg.interceptors;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.parsers.AnnotationParser;
import com.foundersc.pg.parsers.MenuAnnotationParser;
import com.foundersc.pg.views.MenuView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class IndexInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 解析基础数据
        AnnotationParser annotationParser = new MenuAnnotationParser(PageGenerator.getData());
        List<MenuView> menuItems = (List<MenuView>) annotationParser.parse();
        httpServletRequest.getSession().setAttribute("menuItems", menuItems);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
