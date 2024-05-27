package com.example.minicraftserver.domain.work.domain.data

import com.example.minicraftserver.global.enums.ItemCategory
import com.example.minicraftserver.global.enums.ItemType

data class EquipmentStack(
    val id: Long,
    val type: ItemType,
    val category: ItemCategory,
    val health: Int,
    val defense: Int,
    val lucky: Int,
    val isUse: Boolean
)
