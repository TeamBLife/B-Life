package com.blife.blife.domain.library.application

import com.blife.blife.domain.book.service.BookService
import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.library.service.LibraryService
import com.blife.blife.infra.external.booksearchapi.client.KakaoBookSearchClient
import com.blife.blife.infra.external.libapi.Data4libClient

class LibraryUseCase(
	private val libraryService: LibraryService,
	private val kakaoBookSearchClient: KakaoBookSearchClient,
	private val data4libClient: Data4libClient,
	private val bookService: BookService
) {

	/**
	 * NOTE:
	 *  1. API를 통하여 Library Id 가 정보나루에 등록된 도서관인지를 확인한다. // 등록된 도서관이 아니라면 null을 반환
	 *  2. 등록된 도서관이 맞다면 Library 등록시킨다.
	 */
	fun registerLibrary(libId: Long, memberId: Long): Library =
		data4libClient.getLibraryInfo(libId, memberId)
			?.also { libraryService.addLib(it) }
			?: throw TODO("Library 등록 실패")

	fun addLibBook(isbn: Long, libId: Long, totalBookCount: Long) {
		val book = bookService.getBookByIsbn(isbn)
			?: run {
				val result = kakaoBookSearchClient.searchBookDetailInfo(isbn)
				result.first
			}

		val library = libraryService.getLibrary(libId)

		if (book == null) {
			// TODO: 대기열 테이블에 넣어놓을 것.
			throw TODO("Book에 대한 데이터를 가져올 수 없으며, 저장이 추후로 미뤄짐")
		}

		LibBook.of(library, book, totalBookCount)
			.let { libraryService.addLibBook(it) }
	}


}