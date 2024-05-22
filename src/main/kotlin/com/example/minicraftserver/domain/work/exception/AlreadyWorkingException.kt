package com.example.minicraftserver.domain.work.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object AlreadyWorkingException : MinicraftException(
    ErrorCode.ALREADY_WORKING
)
