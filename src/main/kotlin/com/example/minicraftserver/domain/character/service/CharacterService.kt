package com.example.minicraftserver.domain.character.service

import com.example.minicraftserver.domain.character.domain.repository.CharacterRepository
import com.example.minicraftserver.domain.character.exception.CharacterNotFoundException
import com.example.minicraftserver.domain.character.exception.CharacterNotYoursException
import com.example.minicraftserver.domain.character.presentation.dto.response.CharacterResponses
import com.example.minicraftserver.domain.character.presentation.dto.response.WorkStateResponse
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CharacterService(
    private val userFacade: UserFacade,
    private val itemService: ItemService,
    private val characterRepository: CharacterRepository
) {
    fun getAllCharacter(): CharacterResponses {
        return CharacterResponses(
            characterRepository.findByUser_Id(userFacade.getCurrentUser().id).map { character ->
                CharacterResponses.CharacterResponse(
                    character.id,
                    character.name,
                    character.health,
                    0, 0,
                    character.work?.let { WorkStateResponse(it.workType, it.startTime, it.duration, it.regionType) },
                    itemService.getEquipmentResponse().equipments,
                    character.lastDamaged
                )
            }
        )
    }

    fun getCharacter(id: Long): CharacterResponses.CharacterResponse {
        val character = characterRepository.findByIdOrNull(id) ?: throw CharacterNotFoundException
        if (character.user != userFacade.getCurrentUser()) throw CharacterNotYoursException

        return CharacterResponses.CharacterResponse(
            character.id,
            character.name,
            character.health,
            0, 0,
            character.work?.let { WorkStateResponse(it.workType, it.startTime, it.duration, it.regionType) },
            itemService.getEquipmentResponse().equipments,
            character.lastDamaged
        )
    }
}
