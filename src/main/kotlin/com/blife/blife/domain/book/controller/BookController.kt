package com.blife.blife.domain.book.controller

import com.blife.blife.domain.book.application.BookUseCase
import com.blife.blife.domain.book.controller.dto.BookResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController(
	private val bookUseCase: BookUseCase
) {

	@GetMapping("/{isbn}")
	fun getBook(@PathVariable isbn: Long) =
		bookUseCase.searchDetailBookInfo(isbn)
			.let { BookResponse.from(it) }
			.let { ResponseEntity.status(HttpStatus.OK).body(it) }

	@GetMapping("/search/{title}")
	fun searchBookByTitle(
		@PathVariable title: String,
		@RequestParam page: Int,
	) =
		bookUseCase.searchBookListByTitle(title, page)
			.map { BookResponse.from(it) }
			.let { ResponseEntity.status(HttpStatus.OK).body(it) }

	// TODO : 인기 대출 도서 가져오기
}