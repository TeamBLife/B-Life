package com.blife.blife.domain.review.service

import com.blife.blife.domain.book.repository.LibBookRepository
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.model.*
import com.blife.blife.domain.review.repository.LibBookReviewRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LibBookReviewService(
    private val libBookReviewRepository: LibBookReviewRepository,
    private val libBookRepository: LibBookRepository,
    private val memberRepository: MemberRepository,
) {

    fun getReviewListByLibBookId(libBookId: Long, pageable: Pageable): Page<LibBookReviewResponse> {
        val reviewPage = libBookReviewRepository.findByLibBookId(libBookId, pageable)


        return reviewPage.map { review ->
            LibBookReviewResponse(
                id = review.id!!,
                name = review.member.name,
                point = review.point,
                comment = review.comment,
                status = review.status
            )
        }
    }

    fun getAverage(libBookId: Long): AverageScoreDto {
        val reviews = libBookReviewRepository.findAllByLibBookId(libBookId)
        if (reviews.isEmpty()) {
            throw IllegalArgumentException("No reviews in library found for book with ID: $libBookId")
        }

        val averageScore = reviews.map { it.point }.average()

        return AverageScoreDto(averageScore)
    }

    fun getBookReviewInLib(libBookReviewId: Long): LibBookReviewResponse {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw IllegalArgumentException("review")
        return review.toLibBookResponse()
    }

    @Transactional
    fun createBookReviewInLib(libBookId: Long, userId: Long, request: LibBookReviewRequest): LibBookReviewResponse {
        val libBook =
            libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("library book")
        val member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member")
        if (request.point < 1 || request.point > 10) {
            throw IllegalArgumentException("point must be 1 ~ 10")
        }
        val createdReview = libBookReviewRepository.save(
            LibBookReview(
                libBook = libBook,
                comment = request.comment,
                member = member,
                point = request.point,
                status = request.status,
            )
        )
        return createdReview.toLibBookResponse()
    }

    @Transactional
    fun updateBookReviewInLib(
        libBookReviewId: Long, userId: Long,
        request: LibBookReviewRequest,
    ): LibBookReviewResponse {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw IllegalArgumentException("review")
        if (request.point < 1 || request.point > 10) {
            throw IllegalArgumentException("point must be 1 ~ 10")
        }
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        review.point = request.point
        review.comment = request.comment
        review.status = request.status
        val updateReview = libBookReviewRepository.save(review)

        return updateReview.toLibBookResponse()

    }

    fun deleteLibBookReview(libBookReviewId: Long, userId: Long) {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw TODO("no data error")
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        return libBookReviewRepository.delete(review)
    }

}