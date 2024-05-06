package com.example.minicraftserver.global.security.auth

import com.example.minicraftserver.domain.user.domain.repository.UserRepository
import com.example.minicraftserver.domain.user.exception.UserNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(accountId: String): UserDetails {
        val user = userRepository.findByAccountId(accountId) ?: throw UserNotFoundException
        return AuthDetails(user = user)
    }
}