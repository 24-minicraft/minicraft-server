package com.example.minicraftserver.domain.craft.service

import com.example.minicraftserver.domain.craft.presentation.dto.response.RecipeResponse
import com.example.minicraftserver.global.enums.ItemType
import org.springframework.stereotype.Service

@Service
class CraftService {
    /**
     * 모든 래시피 조회
     */
    fun getCrafts(): RecipeResponse {
        return RecipeResponse(
            ItemType.entries.mapNotNull {
                if (it.craft == null) return@mapNotNull null
                RecipeResponse.Recipe(
                    RecipeResponse.RecipeResult(it, it.category, it.craft.resultAmount),
                    it.craft.requirements
                )
            }
        )
    }
}