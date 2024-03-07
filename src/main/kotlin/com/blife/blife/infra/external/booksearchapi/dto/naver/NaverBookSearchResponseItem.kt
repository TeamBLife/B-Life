package com.blife.blife.infra.external.booksearchapi.dto.naver

import com.blife.blife.domain.book.model.Book
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class NaverBookSearchResponseItem(
	val title: String,
	val link: String,
	val image: String,
	val author: String,
	val discount: String,
	val publisher: String,
	val pubdate: String,
	val isbn: String,
	val description: String,
) {
	fun convertToBook() = Book(
		isbn13 = isbn.toLong(),
		title = title,
		description = description,
		author = author,
		coverUrl = image,
		publicationDate = pubdate.let { date ->
			DateTimeFormatter.ofPattern("yyyyMMdd")
				.let { LocalDate.parse(date, it) }.atStartOfDay()
		},
		isbn10 = null
	)
}