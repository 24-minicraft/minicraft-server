package com.example.minicraftserver.domain.item.presentation.dto.response

import com.example.minicraftserver.global.enums.ItemCategory
import com.example.minicraftserver.global.enums.ItemType

data class EquipmentResponse(
    val equipments: List<Equipment>
) {
    data class Equipment(
        val type: ItemType,
        val category: ItemCategory,
        val health: Int,
        val defense: Int,
        val lucky: Int,
        val isUse: Boolean
    )
}