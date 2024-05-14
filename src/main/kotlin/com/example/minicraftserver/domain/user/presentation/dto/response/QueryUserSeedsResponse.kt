package com.example.minicraftserver.domain.user.presentation.dto.response

import jakarta.validation.constraints.NotNull

data class QueryUserSeedsResponse (
    @field:NotNull
    val seeds: Int
)
