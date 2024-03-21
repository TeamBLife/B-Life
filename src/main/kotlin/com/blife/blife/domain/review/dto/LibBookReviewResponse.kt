package com.blife.blife.domain.review.dto

data class LibBookReviewResponse(
    val id: Long,
    val nickname: String,
    val point: Float,
    val comment: String,
    val status: String
) {
    companion object {
        fun from(libBookReviewResponse: LibBookReviewResponse) = LibBookReviewResponse(
            comment = libBookReviewResponse.comment,
            id = libBookReviewResponse.id,
            nickname = libBookReviewResponse.nickname,
            point = libBookReviewResponse.point,
            status = libBookReviewResponse.status
        )
    }
}
