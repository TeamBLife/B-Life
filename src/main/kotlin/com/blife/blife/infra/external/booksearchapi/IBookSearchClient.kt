package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.domain.book.dto.BookResponse

interface IBookSearchClient {

	fun getClintName(): String

	fun searchBookDetailInfo(isbn13: Long): BookResponse?

	fun searchBookListByTitle(title: String, page: Int): List<BookResponse>?
}