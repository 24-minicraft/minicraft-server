package com.example.minicraftserver.domain.market.presentation.dto.response

import com.example.minicraftserver.global.enums.ItemType

data class QueryBuyEquipmentResponse (
    val equipment : List<Equipment>
)

data class Equipment (
    val type: ItemType,
    val health: Int?,
    val defense: Int?,
    val lucky: Int?,
    val price: Int?
)
