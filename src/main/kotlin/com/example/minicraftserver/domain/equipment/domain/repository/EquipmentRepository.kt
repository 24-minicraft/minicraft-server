package com.example.minicraftserver.domain.equipment.domain.repository

import com.example.minicraftserver.domain.equipment.domain.Equipment
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EquipmentRepository : CrudRepository<Equipment, Long>
