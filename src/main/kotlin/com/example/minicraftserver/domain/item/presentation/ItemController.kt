package com.example.minicraftserver.domain.item.presentation

import com.example.minicraftserver.domain.item.presentation.dto.response.EquipmentResponse
import com.example.minicraftserver.domain.item.presentation.dto.response.InventoryResponse
import com.example.minicraftserver.domain.item.service.ItemService
import com.example.minicraftserver.global.enums.ItemType
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
    fun equipment(): EquipmentResponse {
        return itemService.getEquipmentResponse()
    }

    @PatchMapping("/{type}")
    fun gather(@Valid @PathVariable type: ItemType) {
        itemService.gather(type)
    }
}
