package com.example.minicraftserver.domain.work.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.data.ItemStack

data class WorkResponse(
    val health: Int,
    val materials: List<ItemStack>
)
