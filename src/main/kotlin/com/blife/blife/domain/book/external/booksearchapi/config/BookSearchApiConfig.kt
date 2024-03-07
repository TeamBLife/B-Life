package com.blife.blife.domain.book.external.booksearchapi.config

import com.blife.blife.domain.book.external.booksearchapi.kakao.KakaoBookSearchApiService
import com.blife.blife.domain.book.external.booksearchapi.naver.NaverBookSearchApiService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BookSearchApiConfig {
	@Bean
	fun bookSearchApiList(
		kakaoService: KakaoBookSearchApiService, naverService: NaverBookSearchApiService
	) = listOf(kakaoService, naverService)

}