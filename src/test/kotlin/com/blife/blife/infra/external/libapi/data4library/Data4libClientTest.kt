package com.blife.blife.infra.external.libapi.data4library

import com.blife.blife.infra.external.libapi.Data4libClient
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Data4libClientTest(
	private val client: Data4libClient
) : DescribeSpec({

	describe("popularBookList") {
		context("요청을 하면") {
			it("10개의 데이터를 가져온다.") {
				client.popularBookList()!!.size shouldBe 10
			}
		}
	}
})
