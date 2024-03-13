package com.blife.blife.infra.external.booksearchapi.client

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.library.application.error.ExternalErrorCode
import com.blife.blife.infra.external.booksearchapi.dto.kakao.KakaoBookSearchResponse
import com.blife.blife.infra.external.error.ExternalErrorObject
import com.blife.blife.infra.external.error.ExternalErrorUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class KakaoBookSearchClient(
	@Value("\${external.kakao.restApiKey}")
	private val restApiKey: String,
) : IBookSearchClient, ExternalErrorUtil() {

	override fun getClintName() = "KAKAO"

	companion object {
		private const val BASE_URL = "https://dapi.kakao.com/v3/search/book"
	}

	private val restClient = RestClient.builder()
		.baseUrl(BASE_URL)
		.defaultHeader("Authorization", "KakaoAK $restApiKey")
		.build()

	override fun searchBookDetailInfo(isbn13: Long): Pair<Book?, ExternalErrorCode?> {
		val errorObject = ExternalErrorObject()
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("query", isbn13)
					.queryParam("target", "isbn")
					.queryParam("size", 1)
					.build()
			}
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, errorHandler(errorObject))
			.onStatus(HttpStatusCode::is5xxServerError, errorHandler(errorObject))
			.toEntity(KakaoBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData != null && resultData.documents.isNotEmpty())
			Pair(resultData.documents[0].convertToBook(), null)
		else
			Pair(null, errorObject.errorCode)
	}

	override fun searchBookListByTitle(title: String, page: Long): Pair<List<Book>?, ExternalErrorCode?> {
		val errorObject = ExternalErrorObject()
		val responseEntity = restClient.get()
			.uri { builder ->
				builder
					.queryParam("query", title)
					.queryParam("target", "title")
					.queryParam("page", page)
					.build()
			}
			.retrieve()
			.onStatus(HttpStatusCode::is4xxClientError, errorHandler(errorObject))
			.onStatus(HttpStatusCode::is5xxServerError, errorHandler(errorObject))
			.toEntity(KakaoBookSearchResponse::class.java)

		val resultData = responseEntity.body

		return if (resultData !== null && resultData.documents.isNotEmpty())
			Pair(resultData.documents.map { it.convertToBook() }, null)
		else
			Pair(null, errorObject.errorCode)
	}
}