package com.example.minicraftserver.global.security.config

import com.example.minicraftserver.global.error.ExceptionFilter
import com.example.minicraftserver.global.security.jwt.JwtTokenFilter
import com.example.minicraftserver.global.security.jwt.JwtTokenProvider
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.SecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class FilterConfig(
    private val jwtTokeProvider: JwtTokenProvider,
    private val objectMapper: ObjectMapper
) : SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    override fun configure(builder: HttpSecurity) {
        builder.addFilterBefore(JwtTokenFilter(jwtTokeProvider), UsernamePasswordAuthenticationFilter::class.java)
        builder.addFilterBefore(ExceptionFilter(objectMapper), JwtTokenFilter::class.java)
    }

    override fun init(builder: HttpSecurity?) {}
}