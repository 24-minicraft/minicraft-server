package com.example.minicraftserver.domain.work.presentation.dto.request

import com.example.minicraftserver.domain.work.domain.enums.RegionType

data class WorkRequest(
    val region: RegionType,
    val duration: Int
)
