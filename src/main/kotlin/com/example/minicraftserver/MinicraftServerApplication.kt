package com.example.minicraftserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MinicraftServerApplication

fun main(args: Array<String>) {
    runApplication<MinicraftServerApplication>(*args)
}
