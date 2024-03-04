package com.blife.blife.domain.library.model

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Library(
	@Column(name = "member_id")
	@OneToOne(fetch = FetchType.LAZY)
	val member: Member,

	val libName: String,
	val address: String,
	val libId: Long,
	val closed: LocalDateTime,
	val operatingTime: LocalDateTime,
	val tel: String,
	val homepage: String,
	val latitude: Float,
	val longitude: Float,

	@Enumerated(value = EnumType.STRING)
	val region: REGION
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}
