package com.blife.blife.domain.library.config

import com.blife.blife.domain.book.service.BookService
import com.blife.blife.domain.library.application.LibraryUseCase
import com.blife.blife.domain.library.service.LibraryService
import com.blife.blife.infra.external.booksearchapi.client.KakaoBookSearchClient
import com.blife.blife.infra.external.libapi.Data4libClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LibraryConfig(
	private val libraryService: LibraryService,
	private val kakaoBookSearchClient: KakaoBookSearchClient,
	private val data4libClient: Data4libClient,
	private val bookService: BookService
) {

	@Bean
	fun useCaseConfig() = LibraryUseCase(
		libraryService = libraryService,
		kakaoBookSearchClient = kakaoBookSearchClient,
		data4libClient = data4libClient,
		bookService = bookService
	)
}