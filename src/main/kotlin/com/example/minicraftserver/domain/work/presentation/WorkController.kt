package com.example.minicraftserver.domain.work.presentation

import com.example.minicraftserver.domain.work.domain.enums.WorkType
import com.example.minicraftserver.domain.work.presentation.dto.request.WorkRequest
import com.example.minicraftserver.domain.work.presentation.dto.response.WorkResponse
import com.example.minicraftserver.domain.work.service.WorkService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/works")
@RestController
class WorkController(
    private val workService: WorkService
) {

    @PostMapping("/start")
    fun startWork(
        @RequestParam characterId: Long,
        @RequestParam type: WorkType,
        @Valid @RequestBody req: WorkRequest
    ) {
        workService.startWork(characterId, type, req)
    }

    @DeleteMapping("/end")
    fun endWork(
        @RequestParam characterId: Long
    ): WorkResponse {
        return workService.endWork(characterId)
    }
}
