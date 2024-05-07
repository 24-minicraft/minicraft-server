package com.example.minicraftserver.domain.item.domain

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.global.entity.BaseIdEntity
import com.example.minicraftserver.global.enums.ItemType
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_item")
@Entity
class Item(
    id: Long,

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)")
    val itemType: ItemType,

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 0")
    var amount: Int,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseIdEntity(id)
