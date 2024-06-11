package com.example.minicraftserver.domain.character.service

import com.example.minicraftserver.domain.character.domain.Character
import com.example.minicraftserver.domain.character.domain.repository.CharacterRepository
import com.example.minicraftserver.domain.character.exception.CharacterNotFoundException
import com.example.minicraftserver.domain.character.exception.CharacterNotYoursException
import com.example.minicraftserver.domain.character.presentation.dto.request.CreateCharacterRequest
import com.example.minicraftserver.domain.character.presentation.dto.response.CharacterResponses
import com.example.minicraftserver.domain.character.presentation.dto.response.WorkStateResponse
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.domain.user.exception.SeedShortageException
import com.example.minicraftserver.domain.user.facade.UserFacade
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CharacterService(
    private val userFacade: UserFacade,
    private val itemService: ItemService,
    private val characterRepository: CharacterRepository
) {
    fun getAllCharacter(): CharacterResponses {
        return CharacterResponses(
            characterRepository.findByUserId(userFacade.getCurrentUser().id).map { character ->
                val equipments = itemService.getEquipmentResponse().equipments
                CharacterResponses.CharacterResponse(
                    character.id,
                    character.name,
                    character.health,
                    equipments.sumOf { it.defense },
                    equipments.sumOf { it.lucky },
                    character.work?.let { WorkStateResponse(it.workType, it.startTime, it.duration, it.regionType) },
                    equipments,
                    character.lastDamaged
                )
            }
        )
    }

    fun getCharacter(id: Long): CharacterResponses.CharacterResponse {
        val character = characterRepository.findByIdOrNull(id) ?: throw CharacterNotFoundException
        if (character.user != userFacade.getCurrentUser()) throw CharacterNotYoursException

        val equipments = itemService.getEquipmentResponse().equipments
        return CharacterResponses.CharacterResponse(
            character.id,
            character.name,
            character.health,
            equipments.sumOf { it.defense },
            equipments.sumOf { it.lucky },
            character.work?.let { WorkStateResponse(it.workType, it.startTime, it.duration, it.regionType) },
            equipments,
            character.lastDamaged
        )
    }

    @Transactional
    fun createCharacter(request: CreateCharacterRequest) {
        val user = userFacade.getCurrentUser()
        val characterCount = characterRepository.countAllByUserId(user.id)
        val const = 200 * (characterCount * characterCount)

        if (user.seeds < const) throw SeedShortageException

        characterRepository.save(
            Character(
                id = 0,
                name = request.name,
                health = 100,
                lastDamaged = null,
                work = null,
                user = user,
            )
        )

        user.update(const)
    }
}
