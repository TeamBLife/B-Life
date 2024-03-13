package com.blife.blife.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "wait_member")
class WaitMember(
	val email: String,
	val code: Long,
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}