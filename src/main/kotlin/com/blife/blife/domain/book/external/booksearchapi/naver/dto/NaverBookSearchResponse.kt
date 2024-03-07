package com.blife.blife.domain.book.external.booksearchapi.naver.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

data class NaverBookSearchResponse(
	val lastBuildDate: String,
	val total: Int,
	val start: Int,
	val display: Int,
	val items: List<NaverBookSearchResponseItem>
)