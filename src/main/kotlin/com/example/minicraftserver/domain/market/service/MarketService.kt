package com.example.minicraftserver.domain.market.service

import com.example.minicraftserver.domain.market.domain.repository.MarketRepository
import com.example.minicraftserver.domain.market.presentation.dto.response.Material
import com.example.minicraftserver.domain.market.presentation.dto.response.QuerySellMaterialsResponse
import org.springframework.stereotype.Service

@Service
class MarketService(
    private val marketRepository: MarketRepository
) {

    fun querySellMaterials(): QuerySellMaterialsResponse {
        val market = marketRepository.findAll()

        return QuerySellMaterialsResponse(
            materials = market.map {
                Material(
                    it.market,
                    it.sellCost
                )
            }
        )
    }
}