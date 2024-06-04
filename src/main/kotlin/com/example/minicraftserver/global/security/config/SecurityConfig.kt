package com.example.minicraftserver.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val filterConfig: FilterConfig,
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.invoke {
            csrf { disable() }
            formLogin { disable() }

            authorizeHttpRequests {

                authorize(HttpMethod.GET, "/craft", authenticated)
                authorize(HttpMethod.PATCH, "/craft/{type}", authenticated)

                authorize(HttpMethod.GET, "/item/inventory", authenticated)
                authorize(HttpMethod.GET, "/item/equipment", authenticated)
                authorize(HttpMethod.PATCH, "/item/{type}", authenticated)

                authorize(HttpMethod.GET, "/market/materials", authenticated)
                authorize(HttpMethod.GET, "/market/equipments", authenticated)
                authorize(HttpMethod.GET, "/market/sell/{type}", authenticated)
                authorize(HttpMethod.GET, "/market/buy/{type}", authenticated)

                authorize(HttpMethod.POST, "/users/login", permitAll)
                authorize(HttpMethod.GET, "/users/seeds", authenticated)
                authorize(HttpMethod.POST, "/users/signup", permitAll)

                authorize(HttpMethod.DELETE, "/works/end", authenticated)
                authorize(HttpMethod.POST, "/works/start", authenticated)

                authorize(HttpMethod.PATCH, "/characters", authenticated)
                authorize(HttpMethod.GET, "/characters/list", authenticated)
                authorize(HttpMethod.GET, "/characters/{id}", authenticated)
            }

            sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
        }

        http.apply(filterConfig)

        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}
