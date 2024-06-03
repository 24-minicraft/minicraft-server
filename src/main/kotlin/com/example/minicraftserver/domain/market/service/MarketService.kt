package com.example.minicraftserver.domain.market.service

import com.example.minicraftserver.domain.market.domain.repository.MarketRepository
import com.example.minicraftserver.domain.market.presentation.dto.response.Material
import com.example.minicraftserver.domain.market.presentation.dto.response.QuerySellMaterialsResponse
import com.example.minicraftserver.global.enums.ItemType
import org.springframework.stereotype.Service

@Service
class MarketService {

    fun querySellMaterials(): QuerySellMaterialsResponse {
        val items = ItemType.entries
        val startIdx = items.indexOf(ItemType.LOG)
        val endIdx = items.indexOf(ItemType.IRON_ORE)

        val materials = items.slice(startIdx..endIdx)

        return QuerySellMaterialsResponse(
            materials = materials.map {
                Material(
                    it,
                    it.market?.sellPrice
                )
            }
        )
    }
}
