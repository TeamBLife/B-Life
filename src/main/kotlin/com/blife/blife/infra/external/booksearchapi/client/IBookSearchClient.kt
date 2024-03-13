package com.blife.blife.infra.external.booksearchapi.client

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.library.application.error.ExternalErrorCode

interface IBookSearchClient {

	fun getClintName(): String

	fun searchBookDetailInfo(isbn13: Long): Pair<Book?, ExternalErrorCode?>

	fun searchBookListByTitle(title: String, page: Int): Pair<List<Book>?, ExternalErrorCode?>
}