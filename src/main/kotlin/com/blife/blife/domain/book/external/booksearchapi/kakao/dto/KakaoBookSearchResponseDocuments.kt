package com.blife.blife.domain.book.external.booksearchapi.kakao.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoBookSearchResponseDocuments(
	val documents: List<KakaoBookSearchResponse>,
	val meta: KakaoBookSearchResponseMeta
)