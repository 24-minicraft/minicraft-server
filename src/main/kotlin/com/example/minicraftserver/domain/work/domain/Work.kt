package com.example.minicraftserver.domain.work.domain

import com.example.minicraftserver.domain.work.domain.enums.RegionType
import com.example.minicraftserver.domain.work.domain.enums.WorkType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Table(name = "tbl_work")
@Entity
class Work(
    @Id
    val id: Long,

    @NotNull
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'COLLECTION'")
    @Enumerated(EnumType.STRING)
    val workType: WorkType,

    @NotNull
    @Column(columnDefinition = "VARCHAR(7)")
    @Enumerated(EnumType.STRING)
    val regionType: RegionType,

    @NotNull
    val startTime: LocalDateTime,

    @NotNull
    @Column(columnDefinition = "INT DEFAULT 0")
    val duration: Int,
)
