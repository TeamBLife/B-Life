package com.blife.blife.infra.external.booksearchapi.dto.naver

data class NaverBookSearchResponse(
	val lastBuildDate: String,
	val total: Int,
	val start: Int,
	val display: Int,
	val items: List<NaverBookSearchResponseItem>
)