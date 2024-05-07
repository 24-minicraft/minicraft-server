package com.example.minicraftserver.domain.item.service

import com.example.minicraftserver.domain.item.domain.Item
import com.example.minicraftserver.domain.item.domain.repository.ItemRepository
import com.example.minicraftserver.domain.item.presentation.dto.response.InventoryResponse
import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.global.enums.ItemCategory
import com.example.minicraftserver.global.enums.ItemType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.Timer
import kotlin.jvm.optionals.getOrNull

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    /**
     * 유저에게 아이템 추가
     * @property user 대상 유저
     * @property itemType 지급할 아이템 타입
     * @property amount 지급할 아이템 개수 (기본값: 1개)
     */
    @Transactional
    fun add(user: User, itemType: ItemType, amount: Int = 1) {
        val item = getData(user, itemType, true)
        item.amount += amount
    }

    /**
     * 유저에게 아이템 추가
     * @property user 대상 유저
     * @property itemStack 지급할 아이템
     */
    @Transactional
    fun add(user: User, itemStack: ItemStack) {
        add(user, itemStack.type, itemStack.amount)
    }

    /**
     * 유저의 아이템 제거, 충분히 소지하고 있지 않다면 반영되지 않음
     * @property user 대상 유저
     * @property itemType 제거할 아이템 타입
     * @property amount 제거할 아이템 개수 (기본값: 1개)
     * @return 성공적으로 아이템을 제거 했는지의 여부 (아이템을 소지하고 있었는지의 여부)
     */
    @Transactional
    fun remove(user: User, itemType: ItemType, amount: Int = 1): Boolean {
        val item = getData(user, itemType, true)
        if (item.amount < amount) return false
        item.amount -= amount
        return true
    }


    /**
     * 유저의 아이템 제거, 충분히 소지하고 있지 않다면 반영되지 않음
     * @property user 대상 유저
     * @property itemStack 제거할 아이템
     * @return 성공적으로 아이템을 제거 했는지의 여부 (아이템을 소지하고 있는지의 여부)
     */
    @Transactional
    fun remove(user: User, itemStack: ItemStack): Boolean {
        return remove(user, itemStack.type, itemStack.amount)
    }

    /**
     * 유저의 아이템 소지 여부
     * @property user 대상 유저
     * @property itemType 확인할 아이템 타입
     * @property amount 확인할 아이템 개수 (기본값: 1개)
     * @return 아이템을 소지하고 있는지의 여부
     */
    fun has(user: User, itemType: ItemType, amount: Int = 1): Boolean {
        val item = getData(user, itemType)
        return item.amount >= amount
    }


    /**
     * 유저의 아이템 소지 여부
     * @property user 대상 유저
     * @property itemStack 확인할 아이템
     * @return 아이템을 소지하고 있는지의 여부
     */
    fun has(user: User, itemStack: ItemStack): Boolean {
        return has(user, itemStack.type, itemStack.amount)
    }

    /**
     * 유저의 아이템 소지량 조회
     * @property user 대상 유저
     * @property itemType 조회할 아이템 타입
     * @return 소지하고 있는 아이템 수량
     */
    fun get(user: User, itemType: ItemType): Int {
        return getData(user, itemType).amount
    }

    /**
     * 유저의 아이템 전체 조회
     * @property user 대상 유저
     * @return 소지하고 있는 아이템 전체를 [Map]으로 반환
     */
    fun getInventory(user: User): Map<ItemType, Item> {
        val map = hashMapOf<ItemType, Item>()
        itemRepository.findByUser(user).forEach {
            if (it.amount > 0) map[it.itemType] = it
        }
        return map
    }

    /**
     * 유저의 아이템 전체 조회
     * @property user 대상 유저
     * @property filter 필터링 할 아이템 카테고리
     * @return [filter] 카테고리에 해당하는 모든 소지 아이템 조회
     */
    fun getInventory(user: User, vararg filter: ItemCategory): Map<ItemType, Item> {
        val filterSet = setOf(*filter)
        val map = hashMapOf<ItemType, Item>()
        itemRepository.findByUser(user).forEach {
            if (!filterSet.contains(it.itemType.category)) return@forEach
            if (it.amount > 0) map[it.itemType] = it
        }
        return map
    }

    /**
     * 유저의 재료 아이템 전체 조회
     * @property user 대상 유저
     * @return [InventoryResponse]로 [ItemCategory.MATERIAL] 카테고리에 해당하는 아이템만 조회하여 반환
     */
    fun getInventoryResponse(user: User): InventoryResponse {
        return InventoryResponse(
            itemRepository.findByUser(user).mapNotNull {
                if (it.itemType.category != ItemCategory.MATERIAL) return@mapNotNull null
                ItemStack(it.itemType, it.amount)
            }
        )
    }

    private fun getData(user: User, itemType: ItemType, save: Boolean = false): Item {
        return itemRepository.findByUserAndItemType(user, itemType).getOrNull() ?: if (save) itemRepository.save(Item(0, itemType, 0, user))
        else Item(0, itemType, 0, user)
    }
}