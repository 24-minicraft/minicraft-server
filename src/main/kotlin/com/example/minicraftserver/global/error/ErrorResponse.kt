package com.example.minicraftserver.global.error

import com.example.minicraftserver.global.error.exception.MinicraftException

class ErrorResponse(
    val status: Int,
    val message: String,
) {

    companion object {
        fun of(e: MinicraftException): ErrorResponse {
            return ErrorResponse(
                status = e.status,
                message = e.message
            )
        }
    }
}
