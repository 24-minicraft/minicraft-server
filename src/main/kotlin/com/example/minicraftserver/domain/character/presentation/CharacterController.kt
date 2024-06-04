package com.example.minicraftserver.domain.character.presentation

import com.example.minicraftserver.domain.character.presentation.dto.request.CreateCharacterRequest
import com.example.minicraftserver.domain.character.presentation.dto.response.CharacterResponses
import com.example.minicraftserver.domain.character.service.CharacterService
import com.example.minicraftserver.domain.equipment.service.EquipmentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/characters")
@RestController
class CharacterController(
    private val characterService: CharacterService,
    private val equipmentService: EquipmentService
) {

    @GetMapping("/list")
    fun getCharacterList(): CharacterResponses {
        return characterService.getAllCharacter()
    }

    @GetMapping("/{id}")
    fun getCharacter(@PathVariable id: Long): CharacterResponses.CharacterResponse {
        return characterService.getCharacter(id)
    }

    @PatchMapping
    fun equipCharacter(
        @RequestParam characterId: Long,
        @RequestParam equipmentId: Long,
    ) {
        equipmentService.autoEquip(characterId, equipmentId)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createCharacter(@RequestBody request: CreateCharacterRequest) {
        characterService.createCharacter(request)
    }
}
