package com.example.minicraftserver.global.error.exception

class MinicraftException(
    val errorCode: ErrorCode,
) : RuntimeException() {

    val status: Int get() = errorCode.status
    override val message: String get() = errorCode.message
}
