package com.example.minicraftserver.global.error.exception

enum class ErrorCode (
    val status: Int,
    val message: String,
) {

    USER_ALREADY_EXIST(409, "User Already Exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error.");
}
