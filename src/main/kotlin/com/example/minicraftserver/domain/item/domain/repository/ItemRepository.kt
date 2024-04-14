package com.example.minicraftserver.domain.item.domain.repository

import com.example.minicraftserver.domain.item.domain.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Long>
