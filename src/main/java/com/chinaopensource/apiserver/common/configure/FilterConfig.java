package com.chinaopensource.apiserver.common.configure;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
@EnableAutoConfiguration
public class FilterConfig {

	 /**
     * 配置过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(JwtFilter());
        registration.addUrlPatterns("/*");
        registration.setName("jwtFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "jwtFilter")
    public Filter JwtFilter() {
        return new com.chinaopensource.apiserver.common.filter.JwtFilter();
    }
}
