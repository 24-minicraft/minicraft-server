package com.example.minicraftserver.domain.user.entity

import com.example.minicraftserver.global.entity.BaseIdEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_user")
@Entity
class User (

    id: Long,

    @NotNull
    @Column(columnDefinition = "VARCHAR(15)", unique = true)
    val accountId: String,

    @NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,

    @Column(columnDefinition = "INT DEFAULT 0")
    val seeds: Int,
) : BaseIdEntity(id)