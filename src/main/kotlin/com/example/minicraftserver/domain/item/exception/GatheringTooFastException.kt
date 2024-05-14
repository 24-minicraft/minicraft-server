package com.example.minicraftserver.domain.item.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object GatheringTooFastException : MinicraftException(
    ErrorCode.GATHERING_TOO_FAST
)