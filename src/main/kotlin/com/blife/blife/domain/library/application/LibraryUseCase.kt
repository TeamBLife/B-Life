package com.blife.blife.domain.library.application

import com.blife.blife.domain.book.service.BookService
import com.blife.blife.domain.library.controller.dto.UpdateLibBookRequest
import com.blife.blife.domain.library.controller.dto.UpdateLibraryRequest
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
	private infix fun Boolean.then(after: () -> Unit) {
		if (this) after()
	}

	fun registerLibrary(libId: Long, memberId: Long): Library =
		data4libClient.getLibraryInfo(libId, memberId)
			?.also { libraryService.saveLib(it) }
			?: throw TODO("Library 등록 실패")

	fun addLibBook(isbn: Long, libId: Long, totalBookCount: Long) {
		val book = bookService.getBookByIsbn(isbn) ?: run {
			val result = kakaoBookSearchClient.searchBookDetailInfo(isbn)
			result.first ?: run { TODO("result.second를 기반으로 에러 헨들링") }
		}
		val library = libraryService.getLibrary(libId)

		LibBook.of(library, book, totalBookCount)
			.let { libraryService.saveLibBook(it) }
	}

	fun updateLibBook(updateLibBookRequest: UpdateLibBookRequest, libBookId: Long, loginUserId: Long) {
		val libBook = libraryService.getLibBook(libBookId)

		libraryService.checkLibBookOwner(libBookId, loginUserId) then {
			libBook.apply {
				updateLibBookRequest.totalBookCount?.let { totalBookCount = it }
			}.let { libraryService.saveLibBook(it) }
		}
	}

	fun deleteLibBook(libBookId: Long, loginUserId: Long) {
		libraryService.checkLibBookOwner(libBookId, loginUserId) then { libraryService.deleteLibBook(libBookId) }
	}

	fun updateLibrary(updateLibraryRequest: UpdateLibraryRequest, libId: Long, loginUserId: Long) {
		libraryService.getLibrary(libId).takeIf { it.memberId == loginUserId }
			?.apply {
				updateLibraryRequest.closed?.let { closed = it }
				updateLibraryRequest.tel?.let { tel = it }
				updateLibraryRequest.homepage?.let { homepage = it }
				updateLibraryRequest.operatingTime?.let { operatingTime = it }
			}?.let { libraryService.saveLib(it) }
			?: throw TODO("Owner 아님")
	}
}

