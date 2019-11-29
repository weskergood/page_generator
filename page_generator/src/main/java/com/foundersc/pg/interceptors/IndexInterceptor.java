package com.foundersc.pg.interceptors;

import com.foundersc.pg.PageGenerator;
import com.foundersc.pg.parsers.annotations.AnnotationParser;
import com.foundersc.pg.parsers.annotations.MenuAnnotationParser;
import com.foundersc.pg.views.MenuView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 首页拦截器，当进入到首页模板前拦截请求，对首页模板进行一定操作
 */
@Slf4j
public class IndexInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 解析基础数据,使用菜单注解解析器进行注解解析
        AnnotationParser annotationParser = new MenuAnnotationParser(PageGenerator.getData());
        List<MenuView> menuItems = (List<MenuView>) annotationParser.parse();
        // 将菜单显示文本放入到session中，用于前端显示菜单
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
