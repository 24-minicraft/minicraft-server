package com.example.minicraftserver.domain.user.domain

import com.example.minicraftserver.global.entity.BaseIdEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull

@Table(name = "tbl_user")
@Entity
class User(
    id: Long = 0,

    @NotNull
    @Column(columnDefinition = "VARCHAR(15)", unique = true)
    val accountId: String,

    @NotNull
    @Column(columnDefinition = "CHAR(60)")
    val password: String,

    @Column(columnDefinition = "INT DEFAULT 0")
    var seeds: Int,
) : BaseIdEntity(id) {
    override fun equals(other: Any?): Boolean {
        if (other is User) {
            return this.id == other.id
        }
        return super.equals(other)
    }
    fun update(seeds: Int) {
        this.seeds = seeds
    }
}
