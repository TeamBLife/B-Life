package com.blife.blife.domain.member.dto

import org.springframework.stereotype.Service

data class MemberSignupRequest(
    val role: String,
    val name: String,
    val email: String,
    val password: String
)
