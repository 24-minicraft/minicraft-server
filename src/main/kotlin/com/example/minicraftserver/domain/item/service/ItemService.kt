package com.example.minicraftserver.domain.item.service

import com.example.minicraftserver.domain.equipment.domain.repository.EquipmentRepository
import com.example.minicraftserver.domain.item.domain.Item
import com.example.minicraftserver.domain.item.domain.repository.ItemRepository
import com.example.minicraftserver.domain.item.exception.GatheringTooFastException
import com.example.minicraftserver.domain.item.exception.ItemNotGatherException
import com.example.minicraftserver.domain.item.presentation.dto.response.EquipmentResponse
import com.example.minicraftserver.domain.item.presentation.dto.response.InventoryResponse
import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.user.facade.UserFacade
import com.example.minicraftserver.domain.work.domain.data.EquipmentStack
import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.global.enums.ItemCategory
import com.example.minicraftserver.global.enums.ItemType
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import kotlin.jvm.optionals.getOrNull

@Service
class ItemService(
    private val userFacade: UserFacade,
    private val itemRepository: ItemRepository,
    private val equipmentRepository: EquipmentRepository
) {
    companion object {
        private val gatherDelay = ConcurrentHashMap<Pair<Long, ItemType>, Long>()
    }

    /**
     * 유저에게 아이템 추가
     * @property user 대상 유저
     * @property itemType 지급할 아이템 타입
     * @property amount 지급할 아이템 개수 (기본값: 1개)
     */
    @Transactional
    fun add(user: User, itemType: ItemType, amount: Int = 1) {
        val item = itemRepository.findByUserAndItemType(user, itemType).getOrNull() ?: kotlin.run {
            itemRepository.save(Item(0, itemType, amount, user))
            return
        }
        item.amount += amount // 단순히 아이템 추가
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
     * 현재 유저에게 아이템 추가
     * @property itemStack 지급할 아이템
     */
    @Transactional
    fun add(itemStack: ItemStack) {
        add(userFacade.getCurrentUser(), itemStack.type, itemStack.amount)
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
        if (item.amount < amount) return false // 충분히 소지하고 있지 않다면 반영 안됨
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
        return remove(user, itemStack.type, itemStack.amount) // 참조
    }


    /**
     * 현재 유저의 아이템 제거, 충분히 소지하고 있지 않다면 반영되지 않음
     * @property itemStack 제거할 아이템
     * @return 성공적으로 아이템을 제거 했는지의 여부 (아이템을 소지하고 있는지의 여부)
     */
    @Transactional
    fun remove(itemStack: ItemStack): Boolean {
        return remove(userFacade.getCurrentUser(), itemStack.type, itemStack.amount) // 참조
    }

    @Transactional
    fun removeAll(user: User, items: Collection<ItemStack>) {
        items.forEach { remove(user, it) }
    }

    @Transactional
    fun removeAll(items: Collection<ItemStack>) {
        val user = userFacade.getCurrentUser()
        items.forEach { remove(user, it) }
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
        return item.amount >= amount // 단순히 아이템 소지 여부
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
     * 현재 유저의 아이템 소지 여부
     * @property user 대상 유저
     * @property itemType 확인할 아이템 타입
     * @property amount 확인할 아이템 개수 (기본값: 1개)
     * @return 아이템을 소지하고 있는지의 여부
     */
    fun has(itemType: ItemType, amount: Int = 1): Boolean {
        val item = getData(userFacade.getCurrentUser(), itemType)
        return item.amount >= amount // 단순히 아이템 소지 여부
    }


    /**
     * 현재 유저의 아이템 소지 여부
     * @property itemStack 확인할 아이템
     * @return 아이템을 소지하고 있는지의 여부
     */
    fun has(itemStack: ItemStack): Boolean {
        return has(userFacade.getCurrentUser(), itemStack.type, itemStack.amount)
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
     * 현재 유저의 아이템 소지량 조회
     * @property user 대상 유저
     * @property itemType 조회할 아이템 타입
     * @return 소지하고 있는 아이템 수량
     */
    fun get(itemType: ItemType): Int {
        return getData(userFacade.getCurrentUser(), itemType).amount
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
     * @return [InventoryResponse]로 [ItemCategory.MATERIAL] 카테고리에 해당하는 아이템만 조회하여 반환
     */
    fun getInventoryResponse(): InventoryResponse {
        val user = userFacade.getCurrentUser()
        return InventoryResponse(
            itemRepository.findByUser(user).mapNotNull {
                if (it.itemType.category != ItemCategory.MATERIAL) return@mapNotNull null
                ItemStack(it.itemType, it.amount)
            }
        )
    }

    /**
     * 유저의 장착 가능 아이템 전체 조회
     * @return [InventoryResponse]로 [ItemCategory.MATERIAL] 카테고리에 해당하지 않는 모든 아이템을 조회하여 반환
     */
    fun getEquipmentResponse(): EquipmentResponse {
        val user = userFacade.getCurrentUser()
        val set = hashSetOf<ItemType>()
        return EquipmentResponse(
            itemRepository.findByUser(user).mapNotNull {
                if (it.itemType.category == ItemCategory.MATERIAL) return@mapNotNull null
                EquipmentStack(
                    it.id,
                    it.itemType,
                    it.itemType.category,
                    it.itemType.equipment?.health ?: 0,
                    it.itemType.equipment?.defense ?: 0,
                    it.itemType.equipment?.lucky ?: 0,
                    if (set.contains(it.itemType)) false else {
                        set.add(it.itemType)
                        equipmentRepository.existsByUser_IdAndType(user.id, it.itemType)
                    }
                )
            }
        )
    }

    /**
     * 아이템 소지 정보를 조회
     * @property user 대상 유저
     * @property itemType 조회할 아이템 타입
     * @property save 조회 실패시 새롭게 DB에 추가할 것인지의 여부
     * @return [Item]으로 반환, 아이템을 한 번도 소지하지 않았다면 0개 소지로 반환
     */
    private fun getData(user: User, itemType: ItemType, save: Boolean = false): Item {
        return itemRepository.findByUserAndItemType(user, itemType).getOrNull() ?: if (save) itemRepository.save(Item(0, itemType, 0, user))
        else Item(0, itemType, 0, user)
    }

    @Transactional
    fun gather(itemType: ItemType) {
        if (itemType.gather == null) throw ItemNotGatherException

        val user = userFacade.getCurrentUser()
        val now = System.currentTimeMillis()
        val pair = user.id to itemType
        val delay = gatherDelay.getOrPut(pair) { now - 1 }

        if (now < delay) throw GatheringTooFastException

        gatherDelay[pair] = now + itemType.gather
        add(user, itemType)
    }
}
