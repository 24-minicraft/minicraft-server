package com.example.minicraftserver.domain.work.service

import com.example.minicraftserver.domain.character.domain.repository.CharacterRepository
import com.example.minicraftserver.domain.character.exception.CharacterNotFoundException
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.domain.work.domain.Work
import com.example.minicraftserver.domain.work.domain.enums.WorkType
import com.example.minicraftserver.domain.work.domain.repository.WorkRepository
import com.example.minicraftserver.domain.work.exception.NotWorkingException
import com.example.minicraftserver.domain.work.presentation.dto.request.WorkRequest
import com.example.minicraftserver.domain.work.presentation.dto.response.WorkResponse
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.min

@Service
class WorkService(
    private val characterRepository: CharacterRepository,
    private val workRepository: WorkRepository,
    private val itemService: ItemService
) {
    @Transactional
    fun start(characterId: Long, type: WorkType, req: WorkRequest) {
        val character = characterRepository.findByIdOrNull(characterId) ?: throw CharacterNotFoundException
        character.work = workRepository.save(Work(characterId, type, req.region, LocalDateTime.now(), req.duration))
    }

    @Transactional
    fun end(characterId: Long): WorkResponse {
        val character = characterRepository.findByIdOrNull(characterId) ?: throw CharacterNotFoundException
        val work = workRepository.findByIdOrNull(characterId) ?: throw NotWorkingException

        val zoneOffset = ZoneOffset.ofHours(9)
        val duration = min(work.duration, (LocalDateTime.now().toEpochSecond(zoneOffset) - work.startTime.toEpochSecond(zoneOffset)).toInt())

        val items = if (work.workType == WorkType.COLLECTION) {
            work.regionType.getGatherResult(duration, 0).items
        } else {
            val result = work.regionType.getBattleResult(duration, character.getCurrentHealth(), 0)
            character.health = result.remainHealth
            character.lastDamaged = LocalDateTime.now()
            result.items
        }

        items.forEach(itemService::add)

        character.work = null
        workRepository.delete(work)

        return WorkResponse(
            character.health,
            items
        )
    }
}