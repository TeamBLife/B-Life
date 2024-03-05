package com.blife.blife.domain.book.external.booksearchapi.kakao.dto

import com.blife.blife.domain.book.external.booksearchapi.dto.BookSearchApiResponse
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoBookSearchResponse(
	val title: String,
	val contents: String,
	val url: String,
	val isbn: String,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") val datetime: LocalDateTime,
	val authors: List<String>,
	val publisher: String,
	val translators: List<String>,
	val salePrice: Int,
	val price: Int,
	val thumbnail: String,
	val status: String
) {

	fun convertToBookSearchResponse() = BookSearchApiResponse(
		title = title,
		description = contents,
		author = authors.joinToString(","),
		coverUrl = thumbnail,
		isbn10 = isbn.let { it.split(" ")[0].filter { item -> item.isDigit() }.toLong() },
		isbn13 = isbn.let { it.split(" ").let { split -> if (split.size == 2) split[1].toLong() else null } },
		publicationDate = datetime
	)
}