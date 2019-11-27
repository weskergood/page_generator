package com.foundersc.pg.config;

import com.foundersc.pg.interceptors.AddInterceptor;
import com.foundersc.pg.interceptors.IndexInterceptor;
import com.foundersc.pg.interceptors.QueryInterceptor;
import com.foundersc.pg.interceptors.UpdateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pg/admin").setViewName("index");
        registry.addViewController("/pg/views/query").setViewName("common_query");
        registry.addViewController("/pg/views/add").setViewName("common_add");
        registry.addViewController("/pg/views/update").setViewName("common_update");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IndexInterceptor()).addPathPatterns("/pg/admin");
        registry.addInterceptor(new AddInterceptor()).addPathPatterns("/pg/views/add");
        registry.addInterceptor(new QueryInterceptor()).addPathPatterns("/pg/views/query");
        registry.addInterceptor(new UpdateInterceptor()).addPathPatterns("/pg/views/update");
    }
}
