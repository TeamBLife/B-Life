package com.blife.blife.domain.review.model

import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import jakarta.persistence.*

@Entity(name = "libBookReview")
class LibBookReview(
	@ManyToOne
	@JoinColumn(name = "libBook_id")
	var libBook: LibBookEntity,

	@Column(name = "point")
	var point: Float,

	@Column(name = "comment")
	var comment: String,

	@ManyToOne
	@JoinColumn(name = "member_id")
	val member: Member,

	@Column(name = "status")
	var status: String,
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null
}


fun LibBookReview.toLibBookResponse(): LibBookReviewResponse {
	return LibBookReviewResponse(
		id = id!!,
		nickname = member.nickname,
		point = point,
		comment = comment,
		status = status,
	)
}