package com.example.minicraftserver.domain.work.domain.data

import com.example.minicraftserver.global.enums.ItemType

data class ItemStack(
    val type: ItemType,
    val amount: Int = 1
)