package com.example.minicraftserver.domain.character.domain

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.work.domain.Work
import com.example.minicraftserver.global.entity.BaseIdEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_character")
@Entity
class Character (
    id: Long,

    @NotNull
    @Column(columnDefinition = "VARCHAR(15)")
    val name: String,

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 100")
    val health: Int,

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    val work: Work,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseIdEntity(id)
