package com.blife.blife.infra.external.booksearchapi

import com.blife.blife.infra.external.booksearchapi.client.NaverBookSearchClient
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NaverBookSearchClientTest(
	private val client: NaverBookSearchClient
) : DescribeSpec({

	describe("searchBookDetailInfo") {
		context("호출을 하면") {
			val isbn13 = 9788966263158
			it("성공시 제대로 반환한다.") {
				val result = client.searchBookDetailInfo(isbn13)

				result.first!!.title shouldBe "가상 면접 사례로 배우는 대규모 시스템 설계 기초"
			}
		}
	}
	describe("searchBookListByTitle") {
		context("호출을 하면") {
			val title = "아무"
			it("성공시 10개의 데이터를 반환한다.") {
				val result = client.searchBookListByTitle(title, 1)

				result.first!!.size shouldBe 10
			}
		}
	}
})
