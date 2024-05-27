package com.example.minicraftserver.domain.equipment.domain.repository

import com.example.minicraftserver.domain.character.domain.Character
import com.example.minicraftserver.domain.equipment.domain.Equipment
import com.example.minicraftserver.global.enums.ItemType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipmentRepository : CrudRepository<Equipment, Long> {

    fun existsByUser_IdAndType(id: Long, type: ItemType): Boolean


    fun findByCharacter_Id(id: Long): List<Equipment>


    fun deleteByCharacterAndType(character: Character, type: ItemType): Long
}
