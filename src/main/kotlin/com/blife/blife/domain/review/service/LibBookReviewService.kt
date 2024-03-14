package com.blife.blife.domain.review.service

import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.model.LibBookReview
import com.blife.blife.domain.review.model.toLibBookResponse
import com.blife.blife.domain.review.repository.LibBookReviewRepository
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
    private val memberRepository: MemberRepository
) {

    fun getReviewListByLibBookId(libBookId: Long, pageable: Pageable): Page<LibBookReviewResponse> {
        val reviews = libBookReviewRepository.findByLibBookId(libBookId, pageable)

        return reviews.map { review ->
            LibBookReviewResponse(
                id = review.id!!,
                name = review.member.name,
                point = review.point,
                comment = review.comment,
                status = review.status
            )
        }
    }

    @Transactional
    fun createBookReviewInLib(libBookId: Long, userId: Long, request: LibBookReviewRequest): LibBookReviewResponse {
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw TODO("도서관에서 해당 책을 찾을 수 없습니다.")
        val member = memberRepository.findByIdOrNull(userId) ?: throw TODO("해당 유저를 찾을 수 없습니다.")
        if (1 > request.point || request.point > 10) {
            throw TODO("점수는 1점에서 10점 사이여야 합니다.")
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
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw TODO("review가 없습니다.")
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        if (1 > request.point || request.point > 10) {
            throw TODO("점수는 1점에서 10점 사이여야 합니다.")
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

    fun getAverage(libBookReviewId: Long): AverageScoreDto? {
        TODO("Not yet implemented")
    }

}