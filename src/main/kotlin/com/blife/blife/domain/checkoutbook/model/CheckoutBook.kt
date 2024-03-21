package com.blife.blife.domain.checkoutbook.model

import com.blife.blife.domain.checkoutbook.dto.CheckoutResponse
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.model.BookReview
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class CheckoutBook(
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "libBook_id")
	val libBook: LibBookEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	val member: Member,

	@Column(name = "returned")
	var returned: Boolean,

	@Column(name = "checkout_time", updatable = false)
	var checkoutTime: LocalDateTime? = null,

	@Column(name = "return_time")
	var returnTime: LocalDateTime?,

	@Column(name = "due_date")
	val dueDate: LocalDate,

	) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @PrePersist
    fun onPrePersist() {
        checkoutTime = LocalDateTime.now()
    }
}
