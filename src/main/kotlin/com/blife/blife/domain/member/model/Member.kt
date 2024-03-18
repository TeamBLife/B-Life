package com.blife.blife.domain.member.model

import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.enums.MemberStatus
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import jakarta.persistence.*


@Entity
class Member(
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	var role: MemberRole,

	@Column(name = "name")
	var name: String,

	@Column(name = "email", unique = true)
	val email: String,

	@Column(name = "password")
	val password: String,

	@Column(name = "is_deleted")
	var isDeleted: Boolean = false,

	@OneToOne(mappedBy = "member", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
	var library: LibraryEntity? = null // 선택적으로 LibraryEntity를 참조

) {
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	var status: MemberStatus = MemberStatus.WAIT

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null

}