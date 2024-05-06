package com.example.minicraftserver.global.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object InternalServerErrorException : MinicraftException(
    ErrorCode.INTERNAL_SERVER_ERROR
)

