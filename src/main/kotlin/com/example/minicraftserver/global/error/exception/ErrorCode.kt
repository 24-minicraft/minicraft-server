package com.example.minicraftserver.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    PASSWORD_MISMATCHED(400, "Password Mis Matched"),

    TOKEN_EXPIRED(401, "Token Expired"),
    TOKEN_INVALID(401, "Token Invalid"),

    USER_NOT_FOUND(404, "User not found."),

    USER_ALREADY_EXIST(409, "User Already Exist"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error."),

    ITEM_NOT_CRAFT(400, "Item has not craft data"),

    NOT_ENOUGH_REQUIREMENTS(400, "Not enough craft requirements"),

    ITEM_NOT_GATHER(400, "Item is not gatherable"),

    GATHERING_TOO_FAST(403, "Gathering Too Fast"),

    ;
}
