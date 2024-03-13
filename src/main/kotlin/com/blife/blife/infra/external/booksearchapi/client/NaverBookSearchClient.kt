package com.blife.blife.infra.external.booksearchapi.client

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.library.application.error.ExternalErrorCode
import com.blife.blife.infra.external.booksearchapi.dto.naver.NaverBookSearchResponse
import com.blife.blife.infra.external.error.ExternalErrorObject
import com.blife.blife.infra.external.error.ExternalErrorUtil
import net.minidev.json.JSONObject
import net.minidev.json.JSONValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class NaverBookSearchClient(
	@Value("\${external.naver.clientId}")
	private val clientId: String,
	@Value("\${external.naver.clientSecret}")
	private val clientSecret: String,
) : IBookSearchClient, ExternalErrorUtil() {

	override fun getClintName() = "NAVER"

	companion object {
		private const val BASE_URL = "https://openapi.naver.com/v1/search/book_adv.json"
	}

	private val restClient = RestClient.builder()
		.baseUrl(BASE_URL)
		.defaultHeader("X-Naver-Client-Id", clientId)
		.defaultHeader("X-Naver-Client-Secret", clientSecret)
		.defaultStatusHandler(HttpStatusCode::is4xxClientError) { _, response ->
			JSONValue.parse(response.body.reader()) as JSONObject
		}
		.defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
			JSONValue.parse(response.body.reader()) as JSONObject
		}
		.build()


	override fun searchBookDetailInfo(isbn13: Long): Pair<Book?, ExternalErrorCode?> {
		val errorObject = ExternalErrorObject()
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("d_isbn", isbn13)
					.queryParam("display", 1)
					.build()
			}
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, errorHandler(errorObject))
			.onStatus(HttpStatusCode::is5xxServerError, errorHandler(errorObject))
			.toEntity(NaverBookSearchResponse::class.java)

		val resultData = responseEntity.body


		return if (resultData != null && resultData.items.isNotEmpty())
			Pair(resultData.items[0].convertToBook(), null)
		else
			Pair(null, errorObject.errorCode)
	}

	override fun searchBookListByTitle(title: String, page: Int): Pair<List<Book>?, ExternalErrorCode?> {
		val errorObject = ExternalErrorObject()
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("d_titl", title)
					.queryParam("start", page)
					.build()
			}
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, errorHandler(errorObject))
			.onStatus(HttpStatusCode::is5xxServerError, errorHandler(errorObject))
			.toEntity(NaverBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData !== null && resultData.items.isNotEmpty())
			Pair(resultData.items.map { it.convertToBook() }, null)
		else
			Pair(null, errorObject.errorCode)
	}
}