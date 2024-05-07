package com.example.minicraftserver.global.enums

import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.domain.work.domain.enums.RegionType

enum class ItemType(
    val region: RegionType? = null,
    val gather: Int? = null,
    val battle: BattleData? = null,
    val craft: CraftData? = null,
    val category: ItemCategory = ItemCategory.MATERIAL
) {
    // 아이템
    LOG(
        RegionType.PLAINS,
        gather = 4,
    ),
    STRAW(
        RegionType.PLAINS,
        gather = 10
    ),
    WOLF_TOOTH(
        RegionType.PLAINS,
        battle = BattleData(1.0, 1..2, 1)
    ),
    RAW_RABBIT(
        RegionType.PLAINS,
        battle = BattleData(.2, 1..1, 0)
    ),
    RAW_BOAR(
        RegionType.PLAINS,
        battle = BattleData(.1, 1..1, 5)
    ),
    COBBLESTONE(
        RegionType.MINE,
        gather = 2
    ),
    COAL(
        RegionType.MINE,
        gather = 5
    ),
    COPPER_ORE(
        RegionType.MINE,
        gather = 15
    ),
    IRON_ORE(
        RegionType.MINE,
        gather = 4
    ),

    // 장비
    LEATHER_CLOTHES(
        category = ItemCategory.ARMOR
    ),
    CHAIN_ARMOR(
        category = ItemCategory.ARMOR
    ),
    COPPER_ARMOR(
        category = ItemCategory.ARMOR
    ),
    BRONZE_ARMOR(
        category = ItemCategory.ARMOR
    ),
    IRON_ARMOR(
        category = ItemCategory.ARMOR
    ),

    // 조합
    STONE(
        craft = CraftData(ItemStack(COBBLESTONE, 3), ItemStack(COAL, 1))
    ),
    COPPER_INGOT(
        craft = CraftData(ItemStack(COPPER_ORE), ItemStack(COAL, 3))
    ),
    IRON_INGOT(
        craft = CraftData(ItemStack(IRON_ORE), ItemStack(COAL, 12))
    ),
    PLANKS(
        craft = CraftData(4, ItemStack(LOG))
    ),
    STICK(
        craft = CraftData(4, ItemStack(PLANKS, 2))
    ),
    WOOL_TOOL_SET(
        craft = CraftData(ItemStack(PLANKS, 64), ItemStack(STICK, 8)),
        category = ItemCategory.TOOL
    ),
    STONE_TOOL_SET(
        craft = CraftData(ItemStack(STONE, 8), ItemStack(COBBLESTONE, 8), ItemStack(STICK, 8)),
        category = ItemCategory.TOOL
    ),
    COPPER_TOOL_SET(
        craft = CraftData(ItemStack(COPPER_INGOT, 16), ItemStack(STICK, 8)),
        category = ItemCategory.TOOL
    ),
    IRON_TOOL_SET(
        craft = CraftData(ItemStack(IRON_INGOT, 12), ItemStack(STICK, 8)),
        category = ItemCategory.TOOL
    ),
    LOAD(
        craft = CraftData(ItemStack(STRAW, 6))
    ),
    WOLF_TALISMAN(
        craft = CraftData(ItemStack(WOLF_TOOTH, 20), ItemStack(LOAD, 2)),
        category = ItemCategory.ACCESSORY
    ),
    ;

    data class BattleData(
        val chance: Double,
        val drops: IntRange,
        val damage: Int
    )

    data class CraftData(
        val requirements: List<ItemStack>,
        val resultAmount: Int = 1
    ) {
        constructor(vararg requires: ItemStack) : this(requires.toList())
        constructor(amount: Int, vararg requires: ItemStack) : this(requires.toList(), amount)
    }
}
