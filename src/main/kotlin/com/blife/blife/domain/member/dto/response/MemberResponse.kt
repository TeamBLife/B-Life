package com.blife.blife.domain.member.dto.response

import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.enums.MemberStatus

data class MemberResponse(
	val role: MemberRole,
	val email: String,
	val nickname: String,
	val status: MemberStatus
)
