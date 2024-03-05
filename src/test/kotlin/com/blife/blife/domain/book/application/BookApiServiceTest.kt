package com.blife.blife.domain.book.application

import com.blife.blife.domain.book.dto.BookResponse
import com.blife.blife.domain.book.external.booksearchapi.kakao.KakaoBookSearchApiService
import com.blife.blife.domain.book.external.booksearchapi.naver.NaverBookSearchApiService
import com.blife.blife.domain.book.service.BookService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class BookApiServiceTest : DescribeSpec({

	extensions(SpringTestExtension())

	val kakaoApiService = mockk<KakaoBookSearchApiService>()
	val naverApiService = mockk<NaverBookSearchApiService>()
	val bookService = mockk<BookService>()

	val bookApiService = BookApiService(listOf(kakaoApiService, naverApiService), bookService)

	afterContainer { clearMocks(kakaoApiService, naverApiService, bookService) }

	describe("searchDetailBookInfo") {
		context("DB에 데이터가 있다면") {
			every { bookService.getBookByIsbn(any()) } returns BookResponse(
				1,
				1,
				"image",
				"image",
				"description",
				"DB Data",
				LocalDateTime.now()
			)
			it("DB의 데이터를 가져온다.") {
				bookApiService.searchDetailBookInfo(1).title shouldBe "DB Data"
			}
		}

		context("DB에 데이터가 없고 Kakao Api Service가 성공한다면") {
			every { bookService.getBookByIsbn(any()) } returns null

			val response = BookResponse(
				1,
				1,
				"image",
				"image",
				"description",
				"Kakao Data",
				LocalDateTime.now()
			)

			every { kakaoApiService.searchBookDetailInfo(any()) } returns response
			every { bookService.addBook(any()) } returns response

			it("Kakao에서 데이터를 가지고 오고 데이터를 DB에 저장시킨다.") {
				bookApiService.searchDetailBookInfo(1).title shouldBe "Kakao Data"
			}
		}

		context("DB에 데이터가 없고 Kakao Api Service가 실패한다면") {
			every { bookService.getBookByIsbn(any()) } returns null
			every { kakaoApiService.searchBookDetailInfo(any()) } returns null
			every { naverApiService.searchBookDetailInfo(any()) } returns BookResponse(
				1,
				1,
				"image",
				"image",
				"description",
				"Naver Data",
				LocalDateTime.now()
			)
			it("Naver에서 데이터를 가지고 온다.") {
				bookApiService.searchDetailBookInfo(1).title shouldBe "Naver Data"
			}
		}

		context("어느곳에도 데이터가 없다면") {
			every { bookService.getBookByIsbn(any()) } returns null
			every { kakaoApiService.searchBookDetailInfo(any()) } returns null
			every { naverApiService.searchBookDetailInfo(any()) } returns null
			it("에러가 발생한다.") {
				shouldThrow<NotImplementedError> { bookApiService.searchDetailBookInfo(1) }
			}
		}
	}

	describe("searchBookListByTitle") {
		context("요청을 하고 문제가 없을 경우") {
			every { kakaoApiService.searchBookListByTitle(any(), any()) } returns (1 .. 10).map {
				BookResponse(
					1,
					1,
					"image",
					"image",
					"description",
					"Kakao Data",
					LocalDateTime.now()
				)
			}
			it("Kakao에서 데이터를 가지고 온다.") {
				bookApiService.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "Kakao Data"
				}
			}
		}

		context("Kakao에서 에러가 날 경우") {
			every { kakaoApiService.searchBookListByTitle(any(), any()) } returns null
			every { naverApiService.searchBookListByTitle(any(), any()) } returns (1 .. 10).map {
				BookResponse(
					1,
					1,
					"image",
					"image",
					"description",
					"Naver Data",
					LocalDateTime.now()
				)
			}
			it("Naver에서 데이터를 가지고 온다.") {
				bookApiService.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "Naver Data"
				}
			}
		}

		context("External Api가 전부 에러가 날 경우") {
			every { kakaoApiService.searchBookListByTitle(any(), any()) } returns null
			every { naverApiService.searchBookListByTitle(any(), any()) } returns null
			every { bookService.searchBookListByTitle(any(), any()) } returns (1 .. 10).map {
				BookResponse(
					1,
					1,
					"image",
					"image",
					"description",
					"DB Data",
					LocalDateTime.now()
				)
			}
			it("DB에서 가져온다.") {
				bookApiService.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "DB Data"
				}
			}
		}
	}
})
