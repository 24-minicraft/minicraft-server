package com.example.minicraftserver.domain.item.presentation

import com.example.minicraftserver.domain.item.presentation.dto.response.InventoryResponse
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.global.enums.ItemType
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RequestMapping("/item")
@RestController
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/inventory")
    fun inventory(): InventoryResponse {
        return itemService.getInventoryResponse()
    }

    @GetMapping("/equipment")
    fun equipment(): InventoryResponse {
        TODO() // TODO: Equipment Entity Fix Required -> No User Field
    }

    @PatchMapping("/{type}")
    fun gather(@Valid @PathVariable type: ItemType) {
        itemService.gather(type)
    }
}
