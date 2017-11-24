package me.jinkun.rds.config;

import me.jinkun.rds.RdsSysApplication;
import me.jinkun.rds.authorization.interceptor.AuthorizationInterceptor;
import me.jinkun.rds.authorization.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by huguoju on 2016/12/30.
 */
@Configuration
@ComponentScan(basePackageClasses = RdsSysApplication.class, useDefaultFilters = true)
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    @Autowired
    AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册监控拦截器
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("/**");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
    }
}
