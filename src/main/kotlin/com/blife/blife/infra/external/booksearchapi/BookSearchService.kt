package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.service.BookService
import com.blife.blife.domain.library.application.error.ExternalErrorCode
import com.blife.blife.domain.library.application.error.ExternalErrorHandler
import com.blife.blife.infra.external.booksearchapi.client.IBookSearchClient

class BookSearchService(
	private val bookSearchClients: List<IBookSearchClient>,
	private val bookService: BookService
) {
	private val errorHandler: ExternalErrorHandler = {
		when (it) {
			ExternalErrorCode.OVER_QUARTER -> {}
			else -> {}
		}
	}

	/**
	 * @param handler 에러에 대한 핸들링은 주어진 함수에서 처리를 한다.
	 */
	private infix fun <T> Pair<T, ExternalErrorCode?>.errorHandlingIfHasError(handler: ExternalErrorHandler): T {
		if (this.first == null) handler(this.second!!)
		return this.first
	}

	/**
	 * @return 결과 값을 DB에 저장한 후 다시 반환한다.
	 */
	private fun ifClientIsKakaoThenSaveBook(client: IBookSearchClient, result: Book): Book {
		if (client.getClintName() == "KAKAO") bookService.addBook(result)
		return result
	}

	fun searchBookDetailInfo(isbn: Long): Book? {
		for (client in bookSearchClients) {
			val result = client.searchBookDetailInfo(isbn) errorHandlingIfHasError errorHandler
			if (result != null) return ifClientIsKakaoThenSaveBook(client, result)
		}
		return null
	}

	fun searchBookListByTitle(title: String, page: Long): List<Book>? {
		for (client in bookSearchClients.reversed()) {
			val result = client.searchBookListByTitle(title, page) errorHandlingIfHasError errorHandler
			if (result != null) return result
		}
		return null
	}
}