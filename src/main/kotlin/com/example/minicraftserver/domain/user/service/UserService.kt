package com.example.minicraftserver.domain.user.service

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.user.domain.repository.UserRepository
import com.example.minicraftserver.domain.user.exception.UserAlreadyExist
import com.example.minicraftserver.domain.user.presentation.dto.request.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun signUp (request: UserSignUpRequest) {
        if (userRepository.existsByAccountId(request.accountId)) {
            throw UserAlreadyExist
        }

        userRepository.save(
            User(
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
                seeds = 0
            )
        )
    }
}