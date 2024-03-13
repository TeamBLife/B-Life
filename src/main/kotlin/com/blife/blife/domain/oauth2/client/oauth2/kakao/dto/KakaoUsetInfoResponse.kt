package com.blife.blife.domain.oauth2.client.oauth2.kakao.dto

import com.blife.blife.domain.oauth2.client.oauth2.OAuth2LoginUserInfo
import com.blife.blife.domain.oauth2.model.SocialMember
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class KakaoUserInfoResponse(
	id: Long,
	properties: KakaoUserPropertiesResponse,
	kakaoAccount: KakaoUserAccountResponse
) : OAuth2LoginUserInfo(
	provider = SocialMember.OAuth2Provider.KAKAO,
	id = id.toString(),
	nickname = properties.nickname,
	email = kakaoAccount.email
)