package com.blife.blife.domain.review.service

import com.blife.blife.domain.book.repository.BookRepository
import com.blife.blife.domain.book.repository.LibBookRepository
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
    private val bookRepository: BookRepository,
) {
    fun getReviewByBookId(bookId: Long, pageable: Pageable): Page<BookReviewResponse> {
        val reviewPage = bookReviewRepository.findByBookId(bookId, pageable)


        return reviewPage.map { review ->
            BookReviewResponse(
                name = review.member.name,
                point = review.point,
                comment = review.comment
            )
        }
    }

    fun getReviewByLibBookId(libBookId: Long, pageable: Pageable): Page<LibBookReviewResponse> {
        val reviewPage = libBookReviewRepository.findByLibBookId(libBookId, pageable)


        return reviewPage.map { review ->
            LibBookReviewResponse(
                name = review.member.name,
                point = review.point,
                comment = review.comment,
                status = review.status
            )
        }
    }

    @Transactional
    fun createBookReview(bookId: Long,userId :Long, request: BookReviewRequest): BookReviewResponse {
        val book = bookRepository.findById(bookId).orElseThrow { NoSuchElementException("Book not found") }
        val member =
        val createdReview = bookReviewRepository.save(
            BookReview(
                book = book,
                comment = request.comment,
                member =
                point = request.point,

                )
        )
        return createdReview.toResponse()
    }

    @Transactional
    fun createBookReviewInLib(bookId: Long, libraryId: Long, request: LibBookReviewRequest): LibBookReviewResponse {
        val libBook = libBookRepository.findByBookIdAndLibId(bookId, libraryId)
            ?: throw NoSuchElementException("LibBook not found")
        val createdReview = libBookReviewRepository.save(
            LibBookReview(
                libBook = libBook,
                comment = request.comment,
                member = TODO(),
                point = request.point,
                status = request.status,
            )
        )
        return createdReview.toLibBookResponse()
    }

    @Transactional
    fun updateBookReview(bookId: Long, bookReviewId: Long, request: BookReviewRequest): BookReviewResponse {
        val review = bookReviewRepository.findByBookIdAndId(bookId, bookReviewId)
        if (review == null) {
            throw TODO("no data error")
        }
        review.point = request.point
        review.comment = request.comment
        val updateReview = bookReviewRepository.save(review)
        return updateReview.toResponse()
    }

    @Transactional
    fun updateBookReviewInLib(
        libBookId: Long, libBookReviewId: Long,
        request: LibBookReviewRequest,
    ): LibBookReviewResponse {
        val review = libBookReviewRepository.findByLibBookIdAndId(libBookId, libBookReviewId)
        if (review == null) {
            throw TODO("no data error")
        }
        review.point = request.point
        review.comment = request.comment
        review.status = request.status
        val updateReview = libBookReviewRepository.save(review)

        return updateReview.toLibBookResponse()

    }

    fun deleteReview(bookReviewId: Long) {
        val review = bookReviewRepository.findByIdOrNull(bookReviewId) ?: throw TODO("no data error")
        return bookReviewRepository.delete(review)
    }

    fun deleteLibBookReview(libBookReviewId: Long) {
        val review = libBookReviewRepository.findByIdOrNull(libBookReviewId) ?: throw TODO("no data error")
        return libBookReviewRepository.delete(review)
    }

}