package com.example.minicraftserver.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedHeaders("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
            .allowedOrigins("http://localhost:3000", "http://localhost:3001")
    }
}
