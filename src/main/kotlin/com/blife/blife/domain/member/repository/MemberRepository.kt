package com.blife.blife.domain.member.repository

import com.blife.blife.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository <Member, Long> {
}