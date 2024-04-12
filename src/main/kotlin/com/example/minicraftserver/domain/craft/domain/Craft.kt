package com.example.minicraftserver.domain.craft.domain

import com.example.minicraftserver.global.entity.BaseIdEntity
import com.example.minicraftserver.global.enums.ItemType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_craft")
@Entity
class Craft (
    id: Long,

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    val craftType: ItemType,

    @NotNull
    @Column(columnDefinition = "VARCHAR(256)")
    val amount: Int,
) : BaseIdEntity(id)
