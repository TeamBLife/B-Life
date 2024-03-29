package com.blife.blife.domain.book.application

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.service.BookService
import com.blife.blife.domain.library.application.error.ExternalErrorCode
import com.blife.blife.infra.external.booksearchapi.BookSearchService
import com.blife.blife.infra.external.booksearchapi.client.KakaoBookSearchClient
import com.blife.blife.infra.external.booksearchapi.client.NaverBookSearchClient
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
class BookEntityUseCaseTest : DescribeSpec({

	extensions(SpringTestExtension())

	val kakaoClient = mockk<KakaoBookSearchClient>()
	val naverClient = mockk<NaverBookSearchClient>()
	val bookService = mockk<BookService>()

	val bookSearchService = BookSearchService(listOf(kakaoClient, naverClient), bookService)

	val bookUseCase = BookUseCase(bookSearchService, bookService)

	beforeContainer {
		every { kakaoClient.getClintName() } returns "KAKAO"
		every { naverClient.getClintName() } returns "NAVER"
	}

	afterContainer { clearMocks(kakaoClient, naverClient, bookService) }

	describe("searchDetailBookInfo") {
		context("DB에 데이터가 있다면") {
			every { bookService.getBookByIsbn(any()) } returns Book(
				1,
				1,
				"image",
				"image",
				"description",
				"DB Data",
				LocalDateTime.now()
			)
			it("DB의 데이터를 가져온다.") {
				bookUseCase.searchDetailBookInfo(1).title shouldBe "DB Data"
			}
		}

		context("DB에 데이터가 없고 Kakao Api Service가 성공한다면") {
			every { bookService.getBookByIsbn(any()) } returns null
			val book = Book(
				1,
				1,
				"image",
				"image",
				"description",
				"Kakao Data",
				LocalDateTime.now()
			)

			every { kakaoClient.searchBookDetailInfo(any()) } returns Pair(book, null)
			every { bookService.addBook(any()) } returns book

			it("Kakao에서 데이터를 가지고 오고 데이터를 DB에 저장시킨다.") {
				bookUseCase.searchDetailBookInfo(1).title shouldBe "Kakao Data"
			}
		}

		context("DB에 데이터가 없고 Kakao Api Service가 실패한다면") {
			val book = Book(
				1,
				1,
				"image",
				"image",
				"description",
				"Naver Data",
				LocalDateTime.now()
			)
			every { bookService.getBookByIsbn(any()) } returns null
			every { kakaoClient.searchBookDetailInfo(any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			every { naverClient.searchBookDetailInfo(any()) } returns Pair(book, null)

			it("Naver에서 데이터를 가지고 온다.") {
				bookUseCase.searchDetailBookInfo(1).title shouldBe "Naver Data"
			}
		}

		context("어느곳에도 데이터가 없다면") {
			every { bookService.getBookByIsbn(any()) } returns null
			every { kakaoClient.searchBookDetailInfo(any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			every { naverClient.searchBookDetailInfo(any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			it("에러가 발생한다.") {
				shouldThrow<NotImplementedError> { bookUseCase.searchDetailBookInfo(1) }
			}
		}
	}

	describe("searchBookListByTitle") {
		val generateBookListData: (title: String) -> List<Book> = { title ->
			(1L .. 10L).map {
				Book(
					it,
					it,
					"image",
					"image",
					"description",
					title,
					LocalDateTime.now()
				)
			}
		}
		context("요청을 하고 문제가 없을 경우") {
			every { naverClient.searchBookListByTitle(any(), any()) } returns Pair(
				generateBookListData("Naver Data"),
				null
			)
			it("Naver에서 데이터를 가지고 온다.") {
				bookUseCase.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "Naver Data"
				}
			}
		}

		context("Naver에서 에러가 날 경우") {
			every { naverClient.searchBookListByTitle(any(), any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			every { kakaoClient.searchBookListByTitle(any(), any()) } returns Pair(
				generateBookListData("Kakao Data"),
				null
			)
			it("Kakao에서 데이터를 가지고 온다.") {
				bookUseCase.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "Kakao Data"
				}
			}
		}

		context("External Api가 전부 에러가 날 경우") {
			every { kakaoClient.searchBookListByTitle(any(), any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			every { naverClient.searchBookListByTitle(any(), any()) } returns Pair(null, ExternalErrorCode.OVER_QUARTER)
			every { bookService.searchBookListByTitle(any(), any()) } returns generateBookListData("DB Data")
			it("DB에서 가져온다.") {
				bookUseCase.searchBookListByTitle("", 1).forEach {
					it.title shouldBe "DB Data"
				}
			}
		}
	}
})
