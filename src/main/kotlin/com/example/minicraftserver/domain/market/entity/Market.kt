package com.example.minicraftserver.domain.market.entity

import com.example.minicraftserver.global.entity.BaseIdEntity
import com.example.minicraftserver.global.enums.ItemType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_market")
@Entity
class Market (

    id: Long,

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    val market: ItemType,

    @NotNull
    @Column(columnDefinition = "INT(256)")
    val buyCost: Int,

    @NotNull
    @Column(columnDefinition = "INT(256)")
    val sellCost: Int,
) : BaseIdEntity(id)