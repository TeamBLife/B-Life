package com.blife.blife.domain.member.repository

import com.blife.blife.domain.member.model.WaitMember
import org.springframework.data.jpa.repository.JpaRepository

// NOTE: Redis 도입 이후 삭제될 예정
interface WaitMemberRepository : JpaRepository<WaitMember, Long> {
	fun findByEmail(email: String): WaitMember?
}