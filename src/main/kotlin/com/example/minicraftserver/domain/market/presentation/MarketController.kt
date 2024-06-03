package com.example.minicraftserver.domain.market.presentation

import com.example.minicraftserver.domain.market.presentation.dto.response.QuerySellMaterialsResponse
import com.example.minicraftserver.domain.market.service.MarketService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
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
}
