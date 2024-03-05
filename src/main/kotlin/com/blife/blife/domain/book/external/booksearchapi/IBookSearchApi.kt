package com.blife.blife.domain.book.external.booksearchapi

import com.blife.blife.domain.book.external.booksearchapi.dto.BookSearchApiResponse

interface IBookSearchApi {
	fun searchBookDetailInfo(isbn13: Long, page: Int): BookSearchApiResponse?

	fun searchBookListByTitle(title: String, page: Int): List<BookSearchApiResponse>?
}