package com.example.minicraftserver.domain.craft.service

import com.example.minicraftserver.domain.craft.exception.ItemNotCraftException
import com.example.minicraftserver.domain.craft.exception.NotEnoughRequirementsException
import com.example.minicraftserver.domain.craft.presentation.dto.response.RecipeResponse
import com.example.minicraftserver.domain.item.logic.InventoryLogic.hasAll
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.user.facade.UserFacade
import com.example.minicraftserver.global.enums.ItemType
import org.springframework.stereotype.Service

@Service
class CraftService(
    private val userFacade: UserFacade,
    private val itemService: ItemService
) {
    /**
     * 모든 래시피 조회
     */
    fun getCrafts(): RecipeResponse {
        return RecipeResponse(
            // 모든 레시피 빙글 빙글
            ItemType.entries.mapNotNull {
                // 조합 가능한 레시피만 조회하도록
                if (it.craft == null) return@mapNotNull null
                RecipeResponse.Recipe(
                    RecipeResponse.RecipeResult(it, it.category, it.craft.resultAmount),
                    it.craft.requirements
                )
            }
        )
    }

    fun craft(user: User, itemType: ItemType) {
        val inventory = itemService.getInventory(user)

        // 레시피 조회, 없다면 펑
        val craftData = itemType.craft ?: throw ItemNotCraftException

        // 필요한 아이템을 모두 소지했는지 확인
        if (!inventory.hasAll(craftData.requirements)) throw NotEnoughRequirementsException

        // 필요 아이템 제거
        itemService.removeAll(user, craftData.requirements)

        // 제작된 아이템 추가
        itemService.add(user, itemType)
    }

    fun craft(itemType: ItemType) = craft(userFacade.getCurrentUser(), itemType)
}