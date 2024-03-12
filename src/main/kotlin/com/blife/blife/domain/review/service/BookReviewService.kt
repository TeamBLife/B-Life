package com.blife.blife.domain.review.service

import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.BookReviewRequest
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.model.BookReview
import com.blife.blife.domain.review.model.toResponse
import com.blife.blife.domain.review.repository.BookReviewRepository
import com.blife.blife.infra.postgresql.book.JpaBookRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class BookReviewService(
	private val bookReviewRepository: BookReviewRepository,
	private val memberRepository: MemberRepository,
	private val bookRepository: JpaBookRepository,
) {
	fun getReviewListByBookId(bookId: Long, pageable: Pageable): Page<BookReviewResponse> {
		val book = bookRepository.findByIsbn13(bookId) ?: throw TODO("")
		val reviewPage = bookReviewRepository.findByBook(book, pageable)

		return reviewPage.map { review ->
			BookReviewResponse(
				id = review.id!!,
				name = review.member.name,
				point = review.point,
				comment = review.comment
			)
		}
	}

	fun getReviewByBookId(bookReviewId: Long): BookReviewResponse {
		val review =
			bookReviewRepository.findByIdOrNull(bookReviewId) ?: throw IllegalArgumentException("review")

		return review.toResponse()
	}

	@Transactional
	fun createBookReview(bookId: Long, userId: Long, request: BookReviewRequest): BookReviewResponse {
		val book = bookRepository.findByIdOrNull(bookId) ?: throw IllegalArgumentException("book")
		val member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member")
		val createdReview = bookReviewRepository.save(
			BookReview(
				book = book,
				comment = request.comment,
				member = member,
				point = request.point,

				)
		)
		return createdReview.toResponse()
	}

	@Transactional
	fun updateBookReview(bookReviewId: Long, userId: Long, request: BookReviewRequest): BookReviewResponse {
		val review =
			bookReviewRepository.findByIdOrNull(bookReviewId) ?: throw IllegalArgumentException("review")
		if (review.member.id != userId) {
			throw TODO("no authority")
		}
		review.point = request.point
		review.comment = request.comment
		val updateReview = bookReviewRepository.save(review)
		return updateReview.toResponse()
	}

	fun deleteReview(bookReviewId: Long, userId: Long) {
		val review = bookReviewRepository.findByIdOrNull(bookReviewId) ?: throw TODO("no data error")
		if (review.member.id != userId) {
			throw TODO("no authority")
		}
		return bookReviewRepository.delete(review)
	}

	fun getAverage(bookId: Long): AverageScoreDto? {
		TODO("Not yet implemented")
	}

}