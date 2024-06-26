package com.example.minicraftserver.domain.craft.presentation

import com.example.minicraftserver.domain.craft.presentation.dto.response.RecipeResponse
import com.example.minicraftserver.domain.craft.service.CraftService
import com.example.minicraftserver.global.enums.ItemType
import com.example.minicraftserver.global.exception.UnknownTypeException
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/craft")
@RestController
class CraftController(
    private val craftService: CraftService
) {

    @GetMapping
    fun craftList(): RecipeResponse {
        return craftService.getCrafts()
    }

    @PutMapping("/{type}")
    fun craft(@PathVariable type: String) {
        try {
            craftService.craft(ItemType.valueOf(type))
        } catch (_: IllegalArgumentException) {
            throw UnknownTypeException
        }
    }
}
