package com.example.minicraftserver.domain.item.domain

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.global.entity.BaseIdEntity
import com.example.minicraftserver.global.enums.ItemType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_item")
@Entity
class Item(
    id: Long,

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(11)")
    val itemType: ItemType,

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 0")
    val amount: Int,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseIdEntity(id)
