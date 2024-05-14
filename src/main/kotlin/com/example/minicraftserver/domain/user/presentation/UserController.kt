package com.example.minicraftserver.domain.user.presentation

import com.example.minicraftserver.domain.user.presentation.dto.request.UserLonginRequest
import com.example.minicraftserver.domain.user.presentation.dto.request.UserSignUpRequest
import com.example.minicraftserver.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/user")
@RestController
class UserController(
    private val userService: UserService
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun userSignUp(@Valid @RequestBody request: UserSignUpRequest) {
        userService.signUp(request)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    fun userLogin(@Valid @RequestBody request: UserLonginRequest) {
        userService.login(request)
    }

}
