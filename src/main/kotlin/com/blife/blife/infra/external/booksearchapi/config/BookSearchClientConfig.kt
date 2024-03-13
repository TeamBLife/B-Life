package com.blife.blife.infra.external.booksearchapi.config

import com.blife.blife.domain.book.service.BookService
import com.blife.blife.infra.external.booksearchapi.BookSearchService
import com.blife.blife.infra.external.booksearchapi.client.KakaoBookSearchClient
import com.blife.blife.infra.external.booksearchapi.client.NaverBookSearchClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookSearchClientConfig(
	private val kakaoService: KakaoBookSearchClient,
	private val naverService: NaverBookSearchClient,
	private val bookService: BookService
) {
	@Bean
	fun bookSearchService() = BookSearchService(
		listOf(kakaoService, naverService),
		bookService
	)
}