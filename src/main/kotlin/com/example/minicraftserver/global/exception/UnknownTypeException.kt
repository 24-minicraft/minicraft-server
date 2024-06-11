package com.example.minicraftserver.global.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object UnknownTypeException : MinicraftException(
    ErrorCode.UNKNOWN_TYPE
)

