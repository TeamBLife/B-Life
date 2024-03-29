package com.blife.blife.infra.external.libapi

import com.blife.blife.domain.library.model.Library
import com.blife.blife.infra.external.libapi.dto.Data4libBookResponse
import com.blife.blife.infra.external.libapi.dto.lib.Data4libBookLibResponse
import com.blife.blife.infra.external.libapi.dto.popular.Data4libBookPopularResponse
import net.minidev.json.JSONObject
import net.minidev.json.JSONValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.toEntity

@Component
class Data4libClient(
	@Value("\${external.data4library.secretKey}")
	private val secretKey: String,
) {

	private val restClient = RestClient.builder()
		.baseUrl("http://data4library.kr/api")
		.defaultStatusHandler(HttpStatusCode::is4xxClientError) { _, response ->
			JSONValue.parse(response.body.reader()) as JSONObject
		}
		.defaultStatusHandler(HttpStatusCode::is5xxServerError) { _, response ->
			JSONValue.parse(response.body.reader()) as JSONObject
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
			.toEntity<Data4libBookResponse<Data4libBookPopularResponse>>()

		val result = responseEntity.body

		return if (result != null && result.response.docs.isNotEmpty()) {
			result.response.docs.map { it.doc.isbn13.toLong() }
		} else null
	}

	fun getLibraryInfo(libId: Long, memberId: Long): Library? {
		val responseEntity = restClient.get()
			.uri { builder ->
				builder.path("/libSrch")
					.queryParam("authKey", secretKey)
					.queryParam("libCode", libId)
					.queryParam("pageNo", 1)
					.queryParam("pageSize", 1)
					.queryParam("format", "json")
					.build()
			}
			.retrieve()
			.toEntity<Data4libBookResponse<Data4libBookLibResponse>>()

		val result = responseEntity.body

		return if (result != null) {
			result.response.libs[0].lib.convertToLibrary(memberId)
		} else null
	}
}