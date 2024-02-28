package com.blife.blife.domain.library.model

import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*

@Entity
class Library(
	@Column(name = "member_id")
	@OneToOne(fetch = FetchType.LAZY)
	val member: Member,

	val address: String,

	val libId: Long,

	val libName: String,
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}
