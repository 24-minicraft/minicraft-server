package com.example.minicraftserver

import com.example.minicraftserver.global.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(JwtProperties::class)
@SpringBootApplication
class MiniCraftServerApplication

fun main(args: Array<String>) {
    runApplication<MiniCraftServerApplication>(*args)
}
