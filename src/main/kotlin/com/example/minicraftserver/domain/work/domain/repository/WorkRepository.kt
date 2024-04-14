package com.example.minicraftserver.domain.work.domain.repository

import com.example.minicraftserver.domain.work.domain.Work
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkRepository : CrudRepository<Work, Long>
