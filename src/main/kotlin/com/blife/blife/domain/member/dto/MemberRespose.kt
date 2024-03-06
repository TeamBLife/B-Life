package com.blife.blife.domain.member.dto

import com.blife.blife.domain.member.enums.MemberRole

data class MemberRespose(
    val role: MemberRole,
    val name: String,
    val email: String,
)
