package com.example.minicraftserver.domain.user.presentation.dto.request

import jakarta.validation.constraints.NotBlank

data class UserSignUpRequest(
    @field:NotBlank
    val accountId: String,

    @field:NotBlank
    val password: String
)