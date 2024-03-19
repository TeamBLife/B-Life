package com.blife.blife.domain.review.dto

import com.blife.blife.domain.review.model.BookReview

data class BookReviewResponse(
	val id: Long,
	val name: String,
	val point: Float,
	val comment: String
){
	companion object {
		fun from(bookReviewResponse: BookReviewResponse) = BookReviewResponse(
			comment = bookReviewResponse.comment,
			id = bookReviewResponse.id,
			name = bookReviewResponse.name,
			point = bookReviewResponse.point,
		)
	}
}