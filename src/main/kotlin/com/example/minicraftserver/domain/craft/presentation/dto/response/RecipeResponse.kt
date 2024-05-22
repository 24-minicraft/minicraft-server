package com.example.minicraftserver.domain.craft.presentation.dto.response

import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.global.enums.ItemCategory
import com.example.minicraftserver.global.enums.ItemType

data class RecipeResponse(
    val recipes: List<Recipe>
) {
    data class Recipe(
        val result: RecipeResult,
        val requirements: List<ItemStack>
    )

    data class RecipeResult(
        val type: ItemType,
        val category: ItemCategory,
        val amount: Int
    )
}
