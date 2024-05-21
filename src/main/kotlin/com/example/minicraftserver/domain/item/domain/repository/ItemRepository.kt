package com.example.minicraftserver.domain.item.domain.repository

import com.example.minicraftserver.domain.item.domain.Item
import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.global.enums.ItemType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ItemRepository : CrudRepository<Item, Long> {
    fun findByUserAndItemType(user: User, itemType: ItemType): Optional<Item>


    fun findByUser(user: User): List<Item>
}
