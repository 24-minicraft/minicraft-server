package com.example.minicraftserver.domain.craft.domain.repository

import com.example.minicraftserver.domain.craft.domain.Craft
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CraftRepository : CrudRepository<Craft, Long>
