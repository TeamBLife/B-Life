package com.blife.blife.domain.review.dto

data class LibBookReviewResponse(
    val id: Long,
    val name : String,
    val point: Float,
    val comment: String,
    val status: String,
)
