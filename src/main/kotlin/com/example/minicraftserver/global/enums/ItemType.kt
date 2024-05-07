package com.example.minicraftserver.global.enums

import com.example.minicraftserver.domain.work.domain.enums.RegionType

enum class ItemType(
    val region: RegionType? = null,
    val gather: Int? = null,
    val battle: BattleData? = null
) {
    // 아이템
    LOG(RegionType.PLAINS, gather = 4),
    STRAW(RegionType.PLAINS, gather = 10),
    WOLF_TOOTH(RegionType.PLAINS, battle = BattleData(1.0, 1..2, 1)),
    RAW_RABBIT(RegionType.PLAINS, battle = BattleData(.2, 1..1, 0)),
    RAW_BOAR(RegionType.PLAINS, battle = BattleData(.1, 1..1, 5)),
    COBBLESTONE(RegionType.MINE, gather = 2),
    COAL(RegionType.MINE, gather = 5),
    COPPER_ORE(RegionType.MINE, gather = 15),
    IRON_ORE(RegionType.MINE, gather = 4),

    // 장비
    LEATHER_CLOTHES,
    CHAIN_ARMOR,
    COPPER_ARMOR,
    BRONZE_ARMOR,
    IRON_ARMOR;

    data class BattleData(
        val chance: Double,
        val drops: IntRange,
        val damage: Int
    )
}
