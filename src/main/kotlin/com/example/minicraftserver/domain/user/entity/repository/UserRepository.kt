package com.example.minicraftserver.domain.user.entity.repository

import com.example.minicraftserver.domain.user.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>