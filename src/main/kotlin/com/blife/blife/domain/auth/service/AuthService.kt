package com.blife.blife.domain.auth.service

import com.blife.blife.domain.auth.dto.CertificationCheckRequest
import com.blife.blife.domain.member.enums.MemberStatus
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.member.repository.WaitMemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
	private val memberRepository: MemberRepository,
	private val waitMemberRepository: WaitMemberRepository
) {
	@Transactional
	fun checkCertification(request: CertificationCheckRequest) {
		/**
		 * NOTE: 검증
		 *  1. WaitMember Table에서 email 기반으로 가져온다
		 *  2. code가 일치하는지 검증
		 */

		val waitMember = waitMemberRepository.findByEmail(request.email)
			?: throw TODO("대기열에 존재하지 않는 Email")

		if (waitMember.code != request.code)
			throw TODO("인증번호가 일치하지 않음")

		val member = memberRepository.findByEmail(request.email) ?: throw TODO("관리자 문의 필요한 상황")
		member.status = MemberStatus.NORMAL
		waitMemberRepository.delete(waitMember)
	}
}