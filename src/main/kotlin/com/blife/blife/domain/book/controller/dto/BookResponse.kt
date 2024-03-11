package com.blife.blife.domain.book.controller.dto

import com.blife.blife.domain.book.model.Book
import java.time.LocalDateTime

data class BookResponse(
	val isbn10: Long?,
	val isbn13: Long?,
	val coverUrl: String,
	val author: String,
	val description: String,
	val title: String,
	val publicationDate: LocalDateTime
) {
	companion object {
		fun from(book: Book) = BookResponse(
			isbn10 = book.isbn10,
			isbn13 = book.isbn13,
			author = book.author,
			coverUrl = book.coverUrl,
			description = book.description,
			publicationDate = book.publicationDate,
			title = book.title
		)
	}
}