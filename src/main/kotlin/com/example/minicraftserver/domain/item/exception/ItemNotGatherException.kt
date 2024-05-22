package com.example.minicraftserver.domain.item.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object ItemNotGatherException : MinicraftException(
    ErrorCode.ITEM_NOT_GATHER
)
