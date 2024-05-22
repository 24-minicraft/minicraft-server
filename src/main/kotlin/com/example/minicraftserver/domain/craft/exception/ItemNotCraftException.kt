package com.example.minicraftserver.domain.craft.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object ItemNotCraftException : MinicraftException(
    ErrorCode.ITEM_NOT_CRAFT
)