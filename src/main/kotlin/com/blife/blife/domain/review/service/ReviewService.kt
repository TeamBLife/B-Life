package com.blife.blife.domain.review.service

import com.blife.blife.domain.book.repository.BookRepository
import com.blife.blife.domain.book.repository.LibBookRepository
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.BookReviewRequest
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.model.*
import com.blife.blife.domain.review.repository.BookReviewRepository
import com.blife.blife.domain.review.repository.LibBookReviewRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val bookReviewRepository: BookReviewRepository,
    private val libBookReviewRepository: LibBookReviewRepository,
    private val libBookRepository: LibBookRepository,
    private val memberRepository: MemberRepository,
    private val bookRepository: BookRepository,
) {
    fun getReviewListByBookId(bookId: Long, pageable: Pageable): Page<BookReviewResponse> {
        val reviewPage = bookReviewRepository.findByBookId(bookId, pageable)


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
            bookReviewRepository.findById(bookReviewId).orElseThrow { NoSuchElementException("Book review not found") }

        return review.toResponse()
    }

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

    fun getBookReviewInLib(libBookReviewId: Long): LibBookReviewResponse {
        val review = libBookReviewRepository.findById(libBookReviewId)
            .orElseThrow { NoSuchElementException("LibBook review not found") }
        return review.toLibBookResponse()
    }


    @Transactional
    fun createBookReview(bookId: Long, userId: Long, request: BookReviewRequest): BookReviewResponse {
        val book = bookRepository.findById(bookId).orElseThrow { NoSuchElementException("Book not found") }
        val member = memberRepository.findById(userId).orElseThrow { NoSuchElementException("Member not found") }
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
    fun createBookReviewInLib(libBookId: Long, userId: Long, request: LibBookReviewRequest): LibBookReviewResponse {
        val libBook =
            libBookRepository.findById(libBookId).orElseThrow { NoSuchElementException("Library book not found") }
        val member = memberRepository.findById(userId).orElseThrow { NoSuchElementException("Member not found") }

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
    fun updateBookReview(bookReviewId: Long, userId: Long, request: BookReviewRequest): BookReviewResponse {
        val review =
            bookReviewRepository.findById(bookReviewId).orElseThrow { NoSuchElementException("Book review not found") }
        if (review == null) {
            throw TODO("no data error")
        }
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        review.point = request.point
        review.comment = request.comment
        val updateReview = bookReviewRepository.save(review)
        return updateReview.toResponse()
    }

    @Transactional
    fun updateBookReviewInLib(
        libBookReviewId: Long, userId: Long,
        request: LibBookReviewRequest,
    ): LibBookReviewResponse {
        val review = libBookReviewRepository.findById(libBookReviewId)
            .orElseThrow { NoSuchElementException("LibBook review not found") }
        if (review == null) {
            throw TODO("no data error")
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

    fun deleteReview(bookReviewId: Long, userId: Long) {
        val review = bookReviewRepository.findByIdOrNull(bookReviewId) ?: throw TODO("no data error")
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        return bookReviewRepository.delete(review)
    }

    fun deleteLibBookReview(libBookReviewId: Long, userId: Long) {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw TODO("no data error")
        if (review.member.id != userId) {
            throw TODO("no authority")
        }
        return libBookReviewRepository.delete(review)
    }

}