package com.example.minicraftserver.domain.craft.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

class NotEnoughRequirementsException : MinicraftException(ErrorCode.NOT_ENOUGH_REQUIREMENTS) {

    companion object {
        @JvmField
        val EXCEPTION = NotEnoughRequirementsException()
    }
}
