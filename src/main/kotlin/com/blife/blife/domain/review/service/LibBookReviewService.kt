package com.blife.blife.domain.review.service

import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.model.LibBookReview
import com.blife.blife.domain.review.model.toLibBookResponse
import com.blife.blife.domain.review.repository.LibBookReviewRepository
import com.blife.blife.global.exception.BadRequestException
import com.blife.blife.global.exception.ErrorCode
import com.blife.blife.global.exception.ModelNotFoundException
import com.blife.blife.global.exception.UnAuthorizationException
import com.blife.blife.infra.postgresql.library.JpaLibBookRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class LibBookReviewService(
    private val libBookReviewRepository: LibBookReviewRepository,
    private val libBookRepository: JpaLibBookRepository,
    private val memberRepository: MemberRepository,
) {

    fun getReviewListByLibBookId(libBookId: Long, pageable: Pageable): Page<LibBookReviewResponse> {
        val reviews = libBookReviewRepository.findByLibBookId(libBookId, pageable)

        return reviews.map { review ->
            LibBookReviewResponse(
                id = review.id!!,
                nickname = review.member.nickname,
                point = review.point,
                comment = review.comment,
                status = review.status
            )
        }
    }

    @Transactional
    fun createBookReviewInLib(libBookId: Long, userId: Long, request: LibBookReviewRequest): LibBookReviewResponse {
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw ModelNotFoundException(ErrorCode.BOOK_NOT_FOUND)
        val member = memberRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException(ErrorCode.MEMBER_NOT_FOUND)
        if (1 > request.point || request.point > 10) {
            throw BadRequestException(ErrorCode.SCORE_BETWEEN_1_AND_10)
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
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw ModelNotFoundException(ErrorCode.REVIEW_NOT_FOUND)
        if (review.member.id != userId) {
            throw UnAuthorizationException(ErrorCode.NO_PERMISSION)
        }
        if (1 > request.point || request.point > 10) {
            throw ModelNotFoundException(ErrorCode.SCORE_BETWEEN_1_AND_10)
        }
        review.point = request.point
        review.comment = request.comment
        review.status = request.status
        val updateReview = libBookReviewRepository.save(review)

        return updateReview.toLibBookResponse()

    }

    fun deleteLibBookReview(libBookReviewId: Long, userId: Long) {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw ModelNotFoundException(ErrorCode.NO_DATE_EXIST)
        if (review.member.id != userId) {
            throw UnAuthorizationException(ErrorCode.NO_PERMISSION)
        }
        return libBookReviewRepository.delete(review)
    }

    fun getAverage(libBookReviewId: Long): AverageScoreDto? {
        TODO("Not yet implemented")
    }

}