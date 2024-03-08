package com.blife.blife.infra.external.booksearchapi.config

import com.blife.blife.infra.external.booksearchapi.KakaoBookSearchClient
import com.blife.blife.infra.external.booksearchapi.NaverBookSearchClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookSearchClientConfig {

	@Bean
	fun bookSearchApiList(
		kakaoService: KakaoBookSearchClient,
		naverService: NaverBookSearchClient
	) = listOf(kakaoService, naverService)

}