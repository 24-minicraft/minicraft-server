package com.example.minicraftserver.domain.equipment.domain

import com.example.minicraftserver.domain.character.domain.Character
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

@Table(name = "tbl_equipment")
@Entity
class Equipment(
    id: Long,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "")
    val type: ItemType,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "character_id")
    val character: Character,
): BaseIdEntity(id)
