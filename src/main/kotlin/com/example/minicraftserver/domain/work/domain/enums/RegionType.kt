package com.example.minicraftserver.domain.work.domain.enums

import com.example.minicraftserver.domain.work.domain.data.ItemStack
import com.example.minicraftserver.global.enums.ItemType
import java.util.*
import kotlin.random.Random

enum class RegionType {
    MINE,
    PLAINS;

    companion object {
        private var gather = emptyList<List<ItemType>>()
        private var battle = emptyList<List<ItemType>>()
    }

    fun getGatherItems(): List<ItemType> {
        if (gather.isEmpty()) gather = entries.map { region -> ItemType.entries.filter { it.region == region && it.gather != null } }
        return gather[ordinal]
    }

    fun getBattleItems(): List<ItemType> {
        if (battle.isEmpty()) battle = entries.map { region -> ItemType.entries.filter { it.region == region && it.battle != null } }
        return battle[ordinal]
    }

    /**
     * 해당 지역에서의 전투 결과 얻기
     * @property time 총 시간(초)
     * @property health 현재 캐릭터의 체력
     * @property lucky 현재 캐릭터의 행운
     */
    fun getBattleResult(time: Int, health: Int, lucky: Int): BattleResult {
        fun next() = Random.nextInt(30, 121)
        var timeLeft = time
        var nextTime = next()
        var hp = health

        val map: EnumMap<ItemType, Int> = EnumMap<ItemType, Int>(ItemType::class.java)
        while (timeLeft >= nextTime) {
            val itemType = getBattleItems().random()
            itemType.battle?.let {
                if (it.chance >= Random.nextDouble()) return@let
                val amount = Random.nextInt(
                    it.drops.first,
                    it.drops.last + 1
                ) * (1 + lucky / 100 + if (Random.nextDouble() * 100 < lucky % 100) 1 else 0)
                map.merge(itemType, amount, Integer::sum)
                hp -= it.damage
            }
            if (hp <= 0) break
            timeLeft -= nextTime
            nextTime = next()
        }

        return BattleResult(map.map { (key, value) -> ItemStack(key, value) }, hp)
    }

    /**
     * 해당 지역에서의 파견 결과 얻기
     * @property time 총 시간(초)
     * @property lucky 현재 캐릭터의 행운
     */
    fun getGatherResult(time: Int, lucky: Int): GatherResult {
        var timeLeft = time
        val resultMap = hashMapOf<ItemType, Int>()
        val highestTime = getGatherItems().maxOf { it.gather!! * 4 }
        val lowestTime = getGatherItems().minOf { it.gather!! * 4 }
        val itemSet = getGatherItems().toHashSet()
        var itemType: ItemType

        while (timeLeft >= lowestTime) {
            if (timeLeft <= highestTime) itemSet.removeIf { it.gather!! > timeLeft }
            itemType = itemSet.random()
            resultMap.merge(itemType, 1 * (1 + lucky / 100 + if (Random.nextDouble() * 100 < lucky % 100) 1 else 0), Integer::sum)
            timeLeft -= itemType.gather!! * 4
        }
        return GatherResult(resultMap.map { ItemStack(it.key, it.value) })
    }

    data class BattleResult(
        val items: List<ItemStack>,
        val remainHealth: Int
    )

    data class GatherResult(
        val items: List<ItemStack>
    )

}
