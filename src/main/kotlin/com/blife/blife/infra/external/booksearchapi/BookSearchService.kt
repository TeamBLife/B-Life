package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.service.BookService
import com.blife.blife.infra.external.booksearchapi.client.IBookSearchClient

class BookSearchService(
	private val bookSearchClients: List<IBookSearchClient>,
	private val bookService: BookService
) {

	private fun ifClientIsKakaoThenSaveBook(client: IBookSearchClient, result: Book) {
		if (client.getClintName() == "KAKAO") bookService.addBook(result)
	}

	fun searchBookDetailInfo(isbn: Long): Book? {
		for (client in bookSearchClients) {
			val result = client.searchBookDetailInfo(isbn)
			result.first?.let {
				ifClientIsKakaoThenSaveBook(client, it)
				return it
			}
				?: result.second.let { } // TODO: Error Handling 함수 정의하고 호출하기 -> ExternalErrorCode 에 따라 Handling 한다.
		}
		return null
	}

	fun searchBookListByTitle(title: String, page: Long): List<Book>? {
		for (client in bookSearchClients) {
			client.searchBookListByTitle(title, page).let {
				if (it.first != null) return it.first
			}
		}
		return null
	}
}