package com.example.minicraftserver.domain.item.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.data.ItemStack

data class InventoryResponse(
    val materials: List<ItemStack>
)
