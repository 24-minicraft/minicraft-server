package com.example.minicraftserver.domain.craft.presentation

import com.example.minicraftserver.domain.craft.presentation.dto.response.RecipeResponse
import com.example.minicraftserver.domain.craft.service.CraftService
import com.example.minicraftserver.global.enums.ItemType
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/craft")
@RestController
class CraftController(
    private val craftService: CraftService
) {

    @GetMapping
    fun craftList(): RecipeResponse {
        return craftService.getCrafts()
    }

    @PatchMapping("/{type}")
    fun craft(@Valid @PathVariable type: ItemType) {
        craftService.craft(type)
    }
}
