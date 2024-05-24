package com.example.minicraftserver.domain.user.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object UserNotFoundException : MinicraftException(
    ErrorCode.USER_NOT_FOUND
)