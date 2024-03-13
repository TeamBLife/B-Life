package com.blife.blife.domain.oauth2.client.oauth2.naver.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class NaverTokenResponse(
	val accessToken: String
)