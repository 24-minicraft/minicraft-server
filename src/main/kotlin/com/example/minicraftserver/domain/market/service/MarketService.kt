package com.example.minicraftserver.domain.market.service

import com.example.minicraftserver.domain.market.presentation.dto.response.Equipment
import com.example.minicraftserver.domain.market.presentation.dto.response.Material
import com.example.minicraftserver.domain.market.presentation.dto.response.QueryBuyEquipmentResponse
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

    fun queryBuyEquipments(): QueryBuyEquipmentResponse {
        val items = ItemType.entries
        val startIdx = items.indexOf(ItemType.LEATHER_CLOTHES)
        val endIdx = items.indexOf(ItemType.IRON_ARMOR)

        val equipments = items.slice(startIdx..endIdx)

        return QueryBuyEquipmentResponse(
            equipment = equipments.map {
                Equipment(
                    it,
                    it.equipment?.health,
                    it.equipment?.health,
                    it.equipment?.lucky,
                    it.market?.buyPrice
                )
            }
        )
    }
}
