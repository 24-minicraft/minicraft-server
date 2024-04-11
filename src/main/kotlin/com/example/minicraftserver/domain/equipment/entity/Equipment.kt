package com.example.minicraftserver.domain.equipment.entity

import com.example.minicraftserver.domain.equipment.entity.enums.EquipmentType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "tbl_equipment")
@Entity
class Equipment(

    @Id
    @Enumerated(EnumType.STRING)
    val type: EquipmentType,

    @Column(columnDefinition = "INT DEFAULT 0")
    val health: Int,

    @Column(columnDefinition = "INT DEFAULT 0")
    val defense: Int,

    @Column(columnDefinition = "INT DEFAULT 0")
    val luck: Int,
)