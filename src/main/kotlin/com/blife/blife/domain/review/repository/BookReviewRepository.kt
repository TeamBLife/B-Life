package com.blife.blife.domain.review.repository

import com.blife.blife.domain.review.model.BookReview
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookReviewRepository : JpaRepository<BookReview, Long> {
    fun findByBookId(bookId: Long, pageable: Pageable): Page<BookReview>
    fun findAllByBookId(bookId: Long) : List<BookReview>
}
