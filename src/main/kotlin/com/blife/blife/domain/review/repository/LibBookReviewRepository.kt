package com.blife.blife.domain.review.repository

import com.blife.blife.domain.review.model.LibBookReview
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface LibBookReviewRepository : JpaRepository<LibBookReview, Long> {
	fun findByLibBookId(libBookId: Long, pageable: Pageable): Page<LibBookReview>

	fun findAllByLibBookId(libBookId: Long): List<LibBookReview>
}
