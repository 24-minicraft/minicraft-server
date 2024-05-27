package com.example.minicraftserver.domain.item.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object ItemNotFoundException : MinicraftException(
    ErrorCode.ITEM_NOT_FOUND
)
