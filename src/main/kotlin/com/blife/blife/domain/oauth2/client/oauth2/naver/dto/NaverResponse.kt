package com.blife.blife.domain.oauth2.client.oauth2.naver.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class NaverResponse<T>(
	@JsonProperty("resultcode") val code: String,
	val message: String,
	val response: T

	//네이버는 모든 API에 대해 한번 랩핑해서 준다. 확인하기
)