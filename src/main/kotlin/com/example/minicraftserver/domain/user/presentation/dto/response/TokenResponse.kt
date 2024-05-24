package com.example.minicraftserver.domain.user.presentation.dto.response

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: LocalDateTime,
)