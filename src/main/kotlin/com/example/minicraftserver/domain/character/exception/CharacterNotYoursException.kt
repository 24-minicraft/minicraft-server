package com.example.minicraftserver.domain.character.exception

import com.example.minicraftserver.global.error.exception.ErrorCode
import com.example.minicraftserver.global.error.exception.MinicraftException

object CharacterNotYoursException : MinicraftException(
    ErrorCode.CHARACTER_NOT_YOURS
)
