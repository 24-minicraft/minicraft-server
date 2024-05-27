package com.example.minicraftserver.domain.equipment.service

import com.example.minicraftserver.domain.character.domain.repository.CharacterRepository
import com.example.minicraftserver.domain.character.exception.CharacterNotFoundException
import com.example.minicraftserver.domain.equipment.domain.Equipment
import com.example.minicraftserver.domain.equipment.domain.repository.EquipmentRepository
import com.example.minicraftserver.domain.item.domain.repository.ItemRepository
import com.example.minicraftserver.domain.item.exception.ItemNotFoundException
import com.example.minicraftserver.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EquipmentService(
    private val userFacade: UserFacade,
    private val characterRepository: CharacterRepository,
    private val itemRepository: ItemRepository,
    private val equipmentRepository: EquipmentRepository
) {
    fun autoEquip(characterId: Long, itemId: Long) {
        val character = characterRepository.findByIdOrNull(characterId) ?: throw CharacterNotFoundException
        val item = itemRepository.findByIdOrNull(itemId) ?: throw ItemNotFoundException
        if (equipmentRepository.deleteByCharacterAndType(character, item.itemType) <= 0) {
            equipmentRepository.save(Equipment(0, item.itemType, userFacade.getCurrentUser(), character))
        }
    }

    fun equip(characterId: Long, itemId: Long) {
        val character = characterRepository.findByIdOrNull(characterId) ?: throw CharacterNotFoundException
        val item = itemRepository.findByIdOrNull(itemId) ?: throw ItemNotFoundException
        equipmentRepository.save(Equipment(0, item.itemType, userFacade.getCurrentUser(), character))
    }

    fun unEquip(characterId: Long, itemId: Long) {
        val character = characterRepository.findByIdOrNull(characterId) ?: throw CharacterNotFoundException
        val item = itemRepository.findByIdOrNull(itemId) ?: throw ItemNotFoundException
        equipmentRepository.deleteByCharacterAndType(character, item.itemType)
    }
}
