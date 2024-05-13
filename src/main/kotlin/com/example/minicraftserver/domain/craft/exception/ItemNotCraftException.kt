package com.example.minicraftserver.domain.craft.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

class ItemNotCraftException : MinicraftException(ErrorCode.ITEM_NOT_CRAFT) {

    companion object {
        @JvmField
        val EXCEPTION = ItemNotCraftException()
    }
}
