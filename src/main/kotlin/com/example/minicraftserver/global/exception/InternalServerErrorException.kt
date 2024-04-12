package com.example.minicraftserver.global.exception

import com.example.minicraftserver.global.error.exception.ErrorCode.INTERNAL_SERVER_ERROR
import com.example.minicraftserver.global.error.exception.MinicraftException

class InternalServerErrorException : MinicraftException(INTERNAL_SERVER_ERROR) {

    companion object {
        @JvmField
        val EXCEPTION = InternalServerErrorException()
    }
}