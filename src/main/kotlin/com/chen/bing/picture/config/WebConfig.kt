package com.chen.bing.picture.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

/**
 * @author 1806632927@qq.com
 * @date 2019/7/24
 * @description:
 */
@Configuration
class WebConfig: WebMvcConfigurationSupport() {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
    }
}