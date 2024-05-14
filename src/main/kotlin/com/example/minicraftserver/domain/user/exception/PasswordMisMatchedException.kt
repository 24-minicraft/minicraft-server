package com.example.minicraftserver.domain.user.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object PasswordMisMatchedException: MinicraftException(
    ErrorCode.PASSWORD_MISMATCHED
)