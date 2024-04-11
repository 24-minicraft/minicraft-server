package com.example.minicraftserver.domain.item.entity.repository

import com.example.minicraftserver.domain.item.entity.Item
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : CrudRepository<Item, Long>