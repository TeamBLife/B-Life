package com.blife.blife.domain.oauth2.client.oauth2.naver.dto

import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2LoginUserInfo
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class NaverLoginUserInfoResponse(
	id: String,
	nickname: String,
	email: String
) : OAuth2LoginUserInfo(
	provider = Member.OAuth2Provider.NAVER,
	id = id,
	nickname = nickname,
	email = email
)