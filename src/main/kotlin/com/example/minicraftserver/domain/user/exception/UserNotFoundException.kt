package com.example.minicraftserver.domain.user.exception

import com.example.minicraftserver.global.error.exception.ErrorCode.USER_NOT_FOUND
import com.example.minicraftserver.global.error.exception.MinicraftException

object UserNotFoundException : MinicraftException(
    USER_NOT_FOUND
)