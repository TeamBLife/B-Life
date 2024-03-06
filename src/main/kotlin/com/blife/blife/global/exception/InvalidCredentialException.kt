package com.blife.blife.global.exception

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()