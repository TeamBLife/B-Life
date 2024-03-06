package com.blife.blife.domain.member.dto

import jakarta.validation.constraints.Pattern


data class MemberSignupRequest(
    val role: String,
    val name: String,
    val email: String,
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*]).{8,15}$",
        message = "비밀번호는 영어 소문자와 대문자, 숫자, 특수문자(~!@#$%^&*)로 이루어져 있어야 합니다.")
    val password: String
)
