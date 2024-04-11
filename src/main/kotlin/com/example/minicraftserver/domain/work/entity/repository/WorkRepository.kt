package com.example.minicraftserver.domain.work.entity.repository

import com.example.minicraftserver.domain.work.entity.Work
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkRepository : CrudRepository<Work, Long>