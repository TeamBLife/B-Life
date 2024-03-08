package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.domain.book.model.Book

interface IBookSearchClient {

	fun getClintName(): String

	fun searchBookDetailInfo(isbn13: Long): Book?

	fun searchBookListByTitle(title: String, page: Int): List<Book>?
}