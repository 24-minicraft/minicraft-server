package com.example.minicraftserver.domain.character.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.data.EquipmentStack
import java.time.LocalDateTime

data class CharacterResponses(
    val characterList: List<CharacterResponse>
) {
    data class CharacterResponse(
        val id: Long,
        val name: String,
        val health: Int,
        val defense: Int,
        val lucky: Int,
        val work: WorkStateResponse?,
        val equipmentList: List<EquipmentStack>,
        val lastDamageTime: LocalDateTime?
    )
}
