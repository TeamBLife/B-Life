package com.blife.blife.infra.postgresql.book.util

import com.blife.blife.domain.book.model.Book
import com.blife.blife.infra.postgresql.book.entity.BookEntity

open class BookConvertUtil {
	protected fun toEntity(book: Book) = BookEntity(
		description = book.description,
		isbn13 = book.isbn13,
		isbn10 = book.isbn10,
		publicationDate = book.publicationDate,
		author = book.author,
		coverUrl = book.coverUrl,
		bookName = book.title
	)

	protected fun toBook(entity: BookEntity) = Book(
		title = entity.bookName,
		description = entity.description,
		isbn13 = entity.isbn13,
		isbn10 = entity.isbn10,
		publicationDate = entity.publicationDate,
		author = entity.author,
		coverUrl = entity.coverUrl
	)
}