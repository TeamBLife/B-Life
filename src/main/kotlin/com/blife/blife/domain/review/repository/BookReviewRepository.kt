package com.blife.blife.domain.review.repository

import com.blife.blife.domain.review.model.BookReview
import com.blife.blife.infra.postgresql.book.entity.BookEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookReviewRepository : JpaRepository<BookReview, Long> {
	fun findByBook(book: BookEntity, pageable: Pageable): Page<BookReview>
	fun findAllByBook(book: BookEntity): List<BookReview>
}
