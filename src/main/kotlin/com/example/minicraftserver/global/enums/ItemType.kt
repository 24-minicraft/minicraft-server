package com.example.minicraftserver.global.enums

import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.domain.work.domain.enums.RegionType
import com.example.minicraftserver.global.enums.ItemType.MarketData.Companion.buy
import com.example.minicraftserver.global.enums.ItemType.MarketData.Companion.sell

enum class ItemType(
    val region: RegionType? = null,
    val gather: Int? = null,
    val battle: BattleData? = null,
    val craft: CraftData? = null,
    val market: MarketData? = null,
    val equipment: EquipmentData? = null,
    val category: ItemCategory = ItemCategory.MATERIAL
) {
    // 아이템
    LOG(
        RegionType.PLAINS,
        gather = 4,
        market = sell(4)
    ),
    STRAW(
        RegionType.PLAINS,
        gather = 10,
        market = sell(2)
    ),
    WOLF_TOOTH(
        RegionType.PLAINS,
        battle = BattleData(1.0, 1..2, 1),
        market = sell(3)
    ),
    RAW_RABBIT(
        RegionType.PLAINS,
        battle = BattleData(.2, 1..1, 0),
        market = sell(10)
    ),
    RAW_BOAR(
        RegionType.PLAINS,
        battle = BattleData(.1, 1..1, 5),
        market = sell(60)
    ),
    COBBLESTONE(
        RegionType.MINE,
        gather = 2,
        market = sell(2)
    ),
    COAL(
        RegionType.MINE,
        gather = 5,
        market = sell(35)
    ),
    COPPER_ORE(
        RegionType.MINE,
        gather = 15,
        market = sell(200)
    ),
    IRON_ORE(
        RegionType.MINE,
        gather = 45,
        market = sell(500)
    ),

    // 장비
    LEATHER_CLOTHES(
        category = ItemCategory.ARMOR,
        market = buy(100)
    ),
    CHAIN_ARMOR(
        category = ItemCategory.ARMOR,
        market = buy(800)
    ),
    COPPER_ARMOR(
        category = ItemCategory.ARMOR,
        market = buy(1500)
    ),
    BRONZE_ARMOR(
        category = ItemCategory.ARMOR,
        market = buy(4500)
    ),
    IRON_ARMOR(
        category = ItemCategory.ARMOR,
        market = buy(12000)
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

    data class MarketData(
        val sellPrice: Int?,
        val buyPrice: Int?
    ) {
        companion object {
            fun sell(price: Int) = MarketData(price, null)
            fun buy(price: Int) = MarketData(null, price)
        }
    }

    data class EquipmentData(
        val health: Int?,
        val defense: Int?,
        val lucky: Int?
    ) {
        companion object {
            fun armor(health: Int, defense: Int) = EquipmentData(health, defense, null)
            fun lucky(lucky: Int) = EquipmentData(null, null, lucky)
            fun all(health: Int, defense: Int, lucky: Int) = EquipmentData(health, defense, lucky)
        }
    }
}
