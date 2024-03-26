package com.blife.blife.domain.member.dto.response

import com.blife.blife.domain.member.enums.MemberStatus

data class MemberLoginResponse(
	val nickname: String,
	val accessToken: String,
	val status: MemberStatus
)
