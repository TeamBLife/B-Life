package com.blife.blife.domain.book.external.booksearchapi.dto

import java.time.LocalDateTime

data class BookSearchApiResponse(
	val isbn10: Long?,
	val isbn13: Long?,
	val coverUrl: String,
	val author: String,
	val description: String,
	val title: String,
	val publicationDate: LocalDateTime
)