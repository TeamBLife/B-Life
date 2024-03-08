package com.blife.blife.domain.member.dto.request

data class MemberLoginRequest (
    val email: String,
    val password: String,
    val role: String
)

