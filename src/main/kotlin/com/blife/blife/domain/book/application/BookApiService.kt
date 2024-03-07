package com.blife.blife.domain.book.application

import com.blife.blife.domain.book.dto.BookResponse
import com.blife.blife.domain.book.external.booksearchapi.IBookSearchApi
import com.blife.blife.domain.book.service.BookService
import org.springframework.stereotype.Service

@Service
class BookApiService(
	private val bookSearchApiList: List<IBookSearchApi>,
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
	fun searchDetailBookInfo(isbn: Long): BookResponse {
		var result: BookResponse? = bookService.getBookByIsbn(isbn)

		result ?: run loop@{
			bookSearchApiList.forEachIndexed { index, api ->
				result = api.searchBookDetailInfo(isbn)
				if (result != null) {
					if (index == 0) bookService.addBook(result!!)
					return@loop
				}
			}
		}

		return result.takeIf { it != null } ?: throw TODO("데이터가 존재하지 않음")
	}

	fun searchBookListByTitle(title: String, page: Int): List<BookResponse> {
		var result: List<BookResponse>? = null

		run loop@{
			bookSearchApiList.forEach {
				result = it.searchBookListByTitle(title, page)
				if (result != null) return@loop
			}
		}

		result ?: run { result = bookService.searchBookListByTitle(title, page) }

		return result.takeIf { it!!.isNotEmpty() } ?: throw TODO("데이터가 존재하지 않음")
	}
}