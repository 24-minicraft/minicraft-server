package com.example.minicraftserver.global.security.config

import com.example.minicraftserver.global.error.ExceptionFilter
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

class FilterConfig (
    val objectMapper: ObjectMapper,
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    override fun configure(builder: HttpSecurity) {
        val exceptionFilter = ExceptionFilter(objectMapper)
    }
}