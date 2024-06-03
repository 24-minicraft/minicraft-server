package com.example.minicraftserver.domain.market.service

import com.example.minicraftserver.domain.item.domain.repository.ItemRepository
import com.example.minicraftserver.domain.item.exception.ItemNotFoundException
import com.example.minicraftserver.domain.market.presentation.dto.response.Equipment
import com.example.minicraftserver.domain.market.presentation.dto.response.Material
import com.example.minicraftserver.domain.market.presentation.dto.response.QueryBuyEquipmentResponse
import com.example.minicraftserver.domain.market.presentation.dto.response.QuerySellMaterialsResponse
import com.example.minicraftserver.domain.user.facade.UserFacade
import com.example.minicraftserver.domain.user.presentation.dto.response.QueryUserSeedsResponse
import com.example.minicraftserver.global.enums.ItemType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MarketService(
    private val userFacade: UserFacade,
    private val itemRepository: ItemRepository
) {

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
            equipments = equipments.map {
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

    @Transactional
    fun sellMaterials(type: ItemType): QueryUserSeedsResponse {
        val user = userFacade.getCurrentUser()
        val item = itemRepository.findByUserAndItemType(user, type)
            .orElseThrow { ItemNotFoundException }

        item.update()
        user.update(item.itemType.market?.sellPrice!!)

        return QueryUserSeedsResponse(user.seeds)
    }
}
