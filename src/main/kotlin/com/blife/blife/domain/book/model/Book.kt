package com.blife.blife.domain.book.model

import java.time.LocalDateTime

class Book(
	val isbn10: Long?,
	val isbn13: Long,
	val coverUrl: String,
	val author: String,
	val description: String,
	val title: String,
	val publicationDate: LocalDateTime
)