package com.example.minicraftserver.domain.character.domain

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.work.domain.Work
import com.example.minicraftserver.global.entity.BaseIdEntity
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime
import java.time.ZoneOffset

@Table(name = "tbl_character")
@Entity
class Character(
    id: Long,

    @NotNull
    @Column(columnDefinition = "VARCHAR(15)")
    val name: String,

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 100")
    var health: Int,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    var lastDamaged: LocalDateTime?,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    var work: Work?,

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,
) : BaseIdEntity(id) {

    fun getCurrentHealth(): Int {
        val zoneOffset = ZoneOffset.ofHours(9)
        return health + (lastDamaged?.let { (LocalDateTime.now().toEpochSecond(zoneOffset) - it.toEpochSecond(zoneOffset)) / 36 } ?: 0L).toInt()
    }
}
