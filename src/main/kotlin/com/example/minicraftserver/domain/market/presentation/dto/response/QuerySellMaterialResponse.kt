package com.example.minicraftserver.domain.market.presentation.dto.response

import com.example.minicraftserver.global.enums.ItemType

data class QuerySellMaterialsResponse(
    val materials: List<Material>
)

data class Material (
    val type: ItemType,
    val price: Int?
)
