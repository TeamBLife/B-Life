package com.blife.blife.domain.book.external.booksearchapi.kakao

import com.blife.blife.domain.book.dto.BookResponse
import com.blife.blife.domain.book.external.booksearchapi.IBookSearchApi
import com.blife.blife.domain.book.external.booksearchapi.kakao.dto.KakaoBookSearchResponse
import net.minidev.json.JSONObject
import net.minidev.json.JSONValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KakaoBookSearchApiService(
	@Value("\${external.kakao.restApiKey}")
	private val restApiKey: String,
) : IBookSearchApi {


	companion object {
		private const val BASE_URL = "https://dapi.kakao.com/v3/search/book"
	}

	private val restClient = RestClient.builder()
		.baseUrl(BASE_URL)
		.defaultHeader("Authorization", "KakaoAK $restApiKey")
		.defaultStatusHandler(HttpStatusCode::is4xxClientError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.build()

	override fun searchBookDetailInfo(isbn13: Long): BookResponse? {

		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("query", isbn13)
					.queryParam("target", "isbn")
					.build()
			}
			.retrieve()
			.toEntity(KakaoBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData != null && resultData.documents.isNotEmpty())
			resultData.documents[0].convertToBookSearchResponse()
		else
			null
	}

	override fun searchBookListByTitle(title: String, page: Int): List<BookResponse>? {
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("query", title)
					.queryParam("target", "title")
					.queryParam("page", page)
					.build()
			}
			.retrieve()
			.toEntity(KakaoBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData !== null && resultData.documents.isNotEmpty())
			resultData.documents.map { it.convertToBookSearchResponse() }
		else
			null
	}
}