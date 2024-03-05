package com.blife.blife.domain.book.external.booksearchapi

import com.blife.blife.domain.book.dto.BookResponse

interface IBookSearchApi {
	fun searchBookDetailInfo(isbn13: Long): BookResponse?

	fun searchBookListByTitle(title: String, page: Int): List<BookResponse>?
}