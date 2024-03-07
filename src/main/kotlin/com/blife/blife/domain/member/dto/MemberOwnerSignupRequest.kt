package com.blife.blife.domain.member.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern

data class MemberOwnerSignupRequest (
    val name: String,
    val email: String,
    @Schema(example = "비밀번호는 영어 소문자와 대문자, 숫자, 특수문자(~!@#\$%^&*)로 이루어져 있어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*]).{8,15}$",
        message = "비밀번호는 영어 소문자와 대문자, 숫자, 특수문자(~!@#\$%^&*)로 이루어져 있어야 합니다.")
    val password: String,
    val libCode: String
)