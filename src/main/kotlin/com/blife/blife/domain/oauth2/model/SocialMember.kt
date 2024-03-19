package com.blife.blife.domain.oauth2.model

import com.blife.blife.domain.member.enums.MemberRole
import jakarta.persistence.*

@Entity
class SocialMember(

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "social_member_id")
	var id: Long? = null,

	@Enumerated(EnumType.STRING)
	val provider: OAuth2Provider,
	val providerId: String,
	val nickname: String,
	val email: String,
	val role: MemberRole,
) {


	enum class OAuth2Provider {
		KAKAO, NAVER
	}

}