package com.example.minicraftserver.domain.work.domain

import com.example.minicraftserver.domain.work.domain.enums.RegionType
import com.example.minicraftserver.domain.work.domain.enums.WorkType
import com.example.minicraftserver.global.entity.BaseIdEntity
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Table(name = "tbl_work")
@Entity
class Work (
    id: Long,

    @NotNull
    @Column(columnDefinition = "VARCHAR(10) DEFAULT COLLECTION")
    val workType: WorkType,

    @Column(columnDefinition = "VARCHAR(7)")
    val regionType: RegionType,

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    val startTime: LocalDateTime,

    @Column(columnDefinition = "INT DEFAULT 0")
    val duration: Int,
) : BaseIdEntity(id)
