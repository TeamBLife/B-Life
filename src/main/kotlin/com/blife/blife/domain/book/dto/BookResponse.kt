package com.blife.blife.domain.book.dto

import java.time.LocalDateTime

data class BookResponse(
	val isbn10: Long?,
	val isbn13: Long?,
	val coverUrl: String,
	val author: String,
	val description: String,
	val title: String,
	val publicationDate: LocalDateTime
)