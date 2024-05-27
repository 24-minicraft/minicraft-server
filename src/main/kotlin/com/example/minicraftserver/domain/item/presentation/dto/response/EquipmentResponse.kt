package com.example.minicraftserver.domain.item.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.data.EquipmentStack

data class EquipmentResponse(
    val equipments: List<EquipmentStack>
)
