package com.example.minicraftserver.domain.market.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object NotEnoughSeedsException : MinicraftException(
    ErrorCode.NOT_ENOUGH_SEEDS
)
