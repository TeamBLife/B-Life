package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.infra.external.booksearchapi.client.KakaoBookSearchClient
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class KakaoBookSearchClientTest(
	private val client: KakaoBookSearchClient
) : DescribeSpec({
	extensions(SpringTestExtension())

	describe("searchBookDetailInfo") {
		context("호출을 하면") {
			it("성공시 데이터를 제대로 반환한다.") {
				val isbn = 9788966263158
				val result = client.searchBookDetailInfo(isbn)

				result.first!!.title shouldBe "가상 면접 사례로 배우는 대규모 시스템 설계 기초"
			}
		}
	}

	describe("searchBookListByTitle") {
		context("호출을 하면") {
			val title = "아무"
			it("성공시 데이터 10개가 제대로 반환된다.") {
				val result = client.searchBookListByTitle(title, 1)

				result.first!!.size shouldBe 10
			}
		}
	}

})
