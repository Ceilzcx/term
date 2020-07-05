package com.example.term.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptionConfig implements WebMvcConfigurer {

    // 添加拦截器，拦截带有@LoginToken的接口
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**");
    }
    // 添加跨域访问
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedOrigins("*");
    }


    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

}
