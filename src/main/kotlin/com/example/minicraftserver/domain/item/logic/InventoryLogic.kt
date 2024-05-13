package com.example.minicraftserver.domain.item.logic

import com.example.minicraftserver.domain.item.domain.Item
import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.global.enums.ItemType

object InventoryLogic {
    fun Map<ItemType, Item>.has(itemType: ItemType, amount: Int) = (this[itemType]?.amount ?: 0) >= amount

    fun Map<ItemType, Item>.has(itemStack: ItemStack): Boolean = has(itemStack.type, itemStack.amount)

    fun Map<ItemType, Item>.hasAll(items: Collection<ItemStack>): Boolean = items.all { has(it) }
}