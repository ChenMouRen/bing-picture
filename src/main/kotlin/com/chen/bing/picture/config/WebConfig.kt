package com.chen.bing.picture.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

/**
 * @author 1806632927@qq.com
 * @date 2019/7/24
 * @version 1.0
 * @description: 配置Web请求中的Ajax跨域问题
 */
@Configuration
class WebConfig: WebMvcConfigurationSupport() {

    /**
     * 允许所有的跨域请求
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
    }
}