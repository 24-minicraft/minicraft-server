package com.example.minicraftserver.domain.user.domain.repository

import com.example.minicraftserver.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long>
