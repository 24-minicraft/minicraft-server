package com.example.minicraftserver.domain.user.service

import com.example.minicraftserver.domain.user.domain.User
import com.example.minicraftserver.domain.user.domain.repository.UserRepository
import com.example.minicraftserver.domain.user.exception.PasswordMisMatchedException
import com.example.minicraftserver.domain.user.exception.UserAlreadyExist
import com.example.minicraftserver.domain.user.facade.UserFacade
import com.example.minicraftserver.domain.user.presentation.dto.request.UserLonginRequest
import com.example.minicraftserver.domain.user.presentation.dto.request.UserSignUpRequest
import com.example.minicraftserver.domain.user.presentation.dto.response.QueryUserSeedsResponse
import com.example.minicraftserver.domain.user.presentation.dto.response.TokenResponse
import com.example.minicraftserver.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val userFacade: UserFacade,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Transactional
    fun signUp(request: UserSignUpRequest) {
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

    @Transactional
    fun login(request: UserLonginRequest): TokenResponse {
        val user = userFacade.getByAccountId(request.accountId)

        if (!passwordEncoder.matches(request.password, user.password))
            throw PasswordMisMatchedException

        return jwtTokenProvider.getToken(user.accountId)
    }

    @Transactional(readOnly = true)
    fun querySeeds(): QueryUserSeedsResponse {
        val user = userFacade.getCurrentUser()
        return QueryUserSeedsResponse(user.seeds)
    }
}
