package com.blife.blife.domain.member.repository

import com.blife.blife.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
	fun findByEmail(email: String): Member?

	fun existsByProviderAndProviderId(provider: Member.OAuth2Provider, toString: String): Boolean
	fun findByProviderAndProviderId(provider: Member.OAuth2Provider, toString: String): Member?

}