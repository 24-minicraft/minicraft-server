package com.example.minicraftserver.domain.market.presentation

import com.example.minicraftserver.domain.market.presentation.dto.response.QueryBuyEquipmentResponse
import com.example.minicraftserver.domain.market.presentation.dto.response.QuerySellMaterialsResponse
import com.example.minicraftserver.domain.market.service.MarketService
import com.example.minicraftserver.domain.user.presentation.dto.response.QueryUserSeedsResponse
import com.example.minicraftserver.global.enums.ItemType
import com.example.minicraftserver.global.exception.UnknownTypeException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/market")
@RestController
class MarketController(
    private val marketService: MarketService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/materials")
    fun querySellMaterials(): QuerySellMaterialsResponse {
        return marketService.querySellMaterials()
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/equipments")
    fun queryBuyEquipments(): QueryBuyEquipmentResponse {
        return marketService.queryBuyEquipments()
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/sell/{type}")
    fun sellMaterial(@PathVariable("type") type: String): QueryUserSeedsResponse {
        try {
            return marketService.sellMaterials(ItemType.valueOf(type))
        } catch (_: IllegalArgumentException) {
            throw UnknownTypeException
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/buy/{type}")
    fun buyEquipment(@PathVariable("type") type: String): QueryUserSeedsResponse {
        try {
            return marketService.buyEquipment(ItemType.valueOf(type))
        } catch (_: IllegalArgumentException) {
            throw UnknownTypeException
        }
    }
}
