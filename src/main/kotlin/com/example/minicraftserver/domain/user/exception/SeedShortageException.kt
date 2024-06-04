package com.example.minicraftserver.domain.user.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object SeedShortageException : MinicraftException(
    ErrorCode.SEED_SHORTAGE
)