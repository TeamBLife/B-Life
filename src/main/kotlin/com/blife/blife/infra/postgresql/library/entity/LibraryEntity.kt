package com.blife.blife.infra.postgresql.library.entity

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*

@Entity
@Table(name = "library")
class LibraryEntity(
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	val member: Member,

	@Id @Column(unique = true)
	val libId: Long,

	val libName: String,
	val address: String,
	val closed: String,
	val operatingTime: String,
	val tel: String,
	val homepage: String,
	val latitude: Float,
	val longitude: Float,

	@Enumerated(value = EnumType.STRING)
	val region: REGION
)
