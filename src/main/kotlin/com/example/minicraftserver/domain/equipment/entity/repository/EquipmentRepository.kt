package com.example.minicraftserver.domain.equipment.entity.repository

import com.example.minicraftserver.domain.equipment.entity.Equipment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipmentRepository : CrudRepository<Equipment, Long>