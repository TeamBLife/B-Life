package com.blife.blife.domain.member.dto

data class MemberLoginRequest (
    val email: String,
    val password: String,
    val role: String
)

