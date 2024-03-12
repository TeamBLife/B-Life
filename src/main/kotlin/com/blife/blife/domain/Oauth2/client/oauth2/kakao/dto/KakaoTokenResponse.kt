package com.blife.blife.domain.Oauth2.client.oauth2.kakao.dto

import com.blife.blife.domain.Oauth2.model.SocialMember
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class KakaoTokenResponse(
    val accessToken: String
) {
}