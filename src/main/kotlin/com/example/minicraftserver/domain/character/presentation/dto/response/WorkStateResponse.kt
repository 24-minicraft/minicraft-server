package com.example.minicraftserver.domain.character.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.enums.RegionType
import com.example.minicraftserver.domain.work.domain.enums.WorkType
import java.time.LocalDateTime

data class WorkStateResponse(
    val type: WorkType,
    val startTime: LocalDateTime,
    val duration: Int,
    val region: RegionType
)
