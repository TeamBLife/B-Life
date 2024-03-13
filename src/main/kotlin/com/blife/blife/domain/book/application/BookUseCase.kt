package com.blife.blife.domain.book.application

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.service.BookService
import com.blife.blife.infra.external.booksearchapi.BookSearchService
import org.springframework.stereotype.Service


@Service
class BookUseCase(
	private val bookSearchService: BookSearchService,
	private val bookService: BookService
) {
	/**
	 * NOTE: 실행 순서
	 *  - 데이터가 없을 경우
	 *  1. DB에서 조회한다.
	 *  2. 데이터가 DB에 없다면 bookSearchApiService에서 DetailBookInfo를 가져온다.
	 *  3. 없다면 throw를 던져 데이터가 없음을 알린다.
	 *  - DB에 데이터가 없고 api요청시 데이터가 있을 경우
	 *  1. DB에서 조회한다.
	 *  2. 데이터가 DB에 없다면 bookSearchApiService에서 DetailBookInfo를 가져온다.
	 *  3. 있다면 DB에 저장시키고 유저에게 다시 반환 시킨다.
	 */
	fun searchDetailBookInfo(isbn: Long): Book {
		val result: Book? = bookService.getBookByIsbn(isbn)
			?: bookSearchService.searchBookDetailInfo(isbn)

		return result.takeIf { it != null } ?: throw TODO("데이터가 존재하지 않음")
	}


	fun searchBookListByTitle(title: String, page: Long): List<Book> {
		var result: List<Book>? = bookSearchService.searchBookListByTitle(title, page)

		if (result == null) result = bookService.searchBookListByTitle(title, page)

		return result.takeIf { it.isNotEmpty() } ?: throw TODO("데이터가 존재하지 않음")
	}
}