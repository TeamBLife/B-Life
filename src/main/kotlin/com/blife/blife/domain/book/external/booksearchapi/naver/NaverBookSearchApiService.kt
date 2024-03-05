package com.blife.blife.domain.book.external.booksearchapi.naver

import com.blife.blife.domain.book.external.booksearchapi.IBookSearchApi
import com.blife.blife.domain.book.external.booksearchapi.dto.BookSearchApiResponse
import com.blife.blife.domain.book.external.booksearchapi.naver.dto.NaverBookSearchResponse
import net.minidev.json.JSONObject
import net.minidev.json.JSONValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient

class NaverBookSearchApiService(
	@Value("\${Naver_Client_Id}")
	private val clientId: String,
	@Value("\${Naver_Client_Secret}")
	private val clientSecret: String,
) : IBookSearchApi {

	companion object {
		private const val BASE_URL = "https://openapi.naver.com/v1/search/book_adv.json"
	}

	private val restClient = RestClient.builder()
		.baseUrl(BASE_URL)
		.defaultHeader("X-Naver-Client-Id", clientId)
		.defaultHeader("X-Naver-Client-Secret", clientSecret)
		.defaultStatusHandler(HttpStatusCode::is4xxClientError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.build()

	override fun searchBookDetailInfo(isbn13: Long, page: Int): BookSearchApiResponse? {
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("d_isbn", isbn13)
					.queryParam("start", page)
					.build()
			}
			.retrieve()
			.toEntity(NaverBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData != null && resultData.items.isNotEmpty())
			resultData.items[0].convertToBookSearchResponse()
		else
			null
	}

	override fun searchBookListByTitle(title: String, page: Int): List<BookSearchApiResponse>? {
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("d_titl", title)
					.queryParam("start", page)
					.build()
			}
			.retrieve()
			.toEntity(NaverBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData !== null && resultData.items.isNotEmpty())
			resultData.items.map { it.convertToBookSearchResponse() }
		else
			null
	}
}