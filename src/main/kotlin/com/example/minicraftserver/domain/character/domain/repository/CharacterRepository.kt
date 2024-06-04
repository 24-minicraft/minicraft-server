package com.example.minicraftserver.domain.character.domain.repository

import com.example.minicraftserver.domain.character.domain.Character
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository : CrudRepository<Character, Long> {

    fun findByUserId(id: Long): List<Character>
    fun countAllByUserId(userId: Long): Int
}
