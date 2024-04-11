package com.example.minicraftserver.domain.craft.entity.repository

import com.example.minicraftserver.domain.craft.entity.Craft
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CraftRepository : CrudRepository<Craft, Long>