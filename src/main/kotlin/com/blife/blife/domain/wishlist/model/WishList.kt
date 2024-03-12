package com.blife.blife.domain.wishlist.model

import com.blife.blife.domain.member.model.Member
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import jakarta.persistence.*

@Entity(name = "wishList")
class WishList(
	@ManyToOne
	@JoinColumn(name = "lib_book_id")
	var libBook: LibBookEntity,

	@ManyToOne
	@JoinColumn(name = "member_id")
	var member: Member,

	@Column(name = "notification_count")
	var notificationCount: Short,

	@Column(name = "max_notification_count")
	val maxNotificationCount: Short,
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id: Long? = null
}