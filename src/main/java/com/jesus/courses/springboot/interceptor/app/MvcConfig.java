package com.jesus.courses.springboot.interceptor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final HandlerInterceptor interceptorSchedule;

    @Autowired
    public MvcConfig(@Qualifier("interceptorSchedule") HandlerInterceptor interceptorSchedule) {
        this.interceptorSchedule = interceptorSchedule;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorSchedule).excludePathPatterns("/closed");
    }
}
