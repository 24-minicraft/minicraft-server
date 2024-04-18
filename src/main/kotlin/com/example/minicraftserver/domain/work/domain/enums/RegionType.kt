package com.example.minicraftserver.domain.work.domain.enums

import com.example.minicraftserver.global.enums.ItemType
import kotlin.random.Random

enum class RegionType {
    MINE,
    PLAINS;

    companion object {
        private val gather = mutableListOf<List<ItemType>>()
        private val battle = mutableListOf<List<ItemType>>()
    }

    init {
        entries.forEach { region ->
            gather.add(ItemType.entries.filter { it.region == region && it.gather != null })
            battle.add(ItemType.entries.filter { it.region == region && it.battle != null })
        }
    }

    fun getGatherItems(): List<ItemType> = gather[ordinal]

    fun getBattleItems(): List<ItemType> = gather[ordinal]

    /**
     * 해당 지역에서의 전투 결과 얻기
     * @property time 총 시간(초)
     * @property health 현재 캐릭터의 체력
     * @property lucky 현재 캐릭터의 행운
     */
    fun getBattle(time: Int, health: Int, lucky: Int): BattleResult {
        val multiply = time / 60
        var count: Int
        var hp = health
        return BattleResult(
            getBattleItems().mapNotNull { itemType ->
                itemType.battle?.let {
                    if (hp <= 0) return@mapNotNull null
                    count = 0
                    for (i in 1..multiply) {
                        if (Random.nextDouble() < it.chance) {
                            count += Random.nextInt(
                                it.drops.first,
                                it.drops.last + 1
                            ) * (1 + lucky / 100 + if (Random.nextDouble() * 100 < lucky % 100) 1 else 0)
                            hp -= it.damage
                            if (hp <= 0) break
                        }
                    }
                    itemType to count
                }
            },
            hp
        )
    }

    /**
     * 해당 지역에서의 파견 결과 얻기
     * @property time 총 시간(초)
     * @property lucky 현재 캐릭터의 행운
     */
    fun getGather(time: Int, lucky: Int): GatherResult {
        var timeLeft = time
        val resultMap = hashMapOf<ItemType, Int>()
        val highestTime = getGatherItems().maxOf { (it.gather ?: -99999) * 4 }
        val lowestTime = getGatherItems().minOf { (it.gather ?: 99999) * 4 }
        val itemSet = getGatherItems().toHashSet()
        var itemType: ItemType

        while (timeLeft >= lowestTime) {
            itemType = itemSet.random()
            resultMap.merge(itemType, 1, Integer::sum)
            timeLeft -= itemType.gather!! * 4 * (1 + lucky / 100 + if (Random.nextDouble() * 100 < lucky % 100) 1 else 0)
            if (timeLeft > highestTime) {
                itemSet.removeIf { it.gather!! > timeLeft }
            }
        }
        return GatherResult(
            resultMap.map { it.key to it.value }
        )
    }

    data class BattleResult(
        val items: List<Pair<ItemType, Int>>,
        val remainHealth: Int
    )

    data class GatherResult(
        val items: List<Pair<ItemType, Int>>
    )
}
