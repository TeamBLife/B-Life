package com.blife.blife.domain.book.external.libapi.data4library

import com.blife.blife.domain.book.external.libapi.data4library.dto.popular.Data4libBookPopularResponse
import net.minidev.json.JSONObject
import net.minidev.json.JSONValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.RestClient

class Data4libApiService(
	@Value("\${Data4Lib_Secret_Key}")
	private val secretKey: String,
) {

	private val restClient = RestClient.builder()
		.baseUrl("http://data4library.kr/api")
		.defaultStatusHandler(HttpStatusCode::is4xxClientError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
			val resObject = JSONValue.parse(response.body.reader()) as JSONObject
		}
		.build()

	/**
	 * TODO: 캐싱 필수
	 *  1. api 응답이 상당히 오래 걸린다.
	 */
	fun popularBookList(): List<Long>? {
		val responseEntity = restClient.get()
			.uri { builder ->
				builder.path("/loanItemSrch")
					.queryParam("authKey", secretKey)
					.queryParam("pageNo", 1)
					.queryParam("pageSize", 10)
					.queryParam("format", "json")
					.build()
			}
			.retrieve()
			.toEntity(Data4libBookPopularResponse::class.java)

		val result = responseEntity.body

		return if (result != null && result.response.docs.isNotEmpty()) {
			result.response.docs.map { it.doc.isbn13.toLong() }
		} else null
	}
}