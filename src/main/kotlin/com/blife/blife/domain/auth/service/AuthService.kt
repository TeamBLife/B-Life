package com.blife.blife.domain.auth.service

import com.blife.blife.domain.auth.dto.CertificationCheckRequest
import com.blife.blife.domain.member.enums.MemberStatus
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.member.repository.WaitMemberRepository
import com.blife.blife.global.exception.CustomException
import com.blife.blife.global.exception.ErrorCode
import com.blife.blife.global.exception.ModelNotFoundException
import com.blife.blife.global.exception.UnAuthorizationException
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
			?: throw ModelNotFoundException(ErrorCode.DOES_NOT_EXIST_EMAIL)

		if (waitMember.code != request.code)
			throw ModelNotFoundException(ErrorCode.NUMBER_DOES_NOT_MATCH)

		val member = memberRepository.findByEmail(request.email)
			?: throw UnAuthorizationException(ErrorCode.PLEASE_CONTACT_ADMINISTRATOR)
		member.status = MemberStatus.NORMAL
		waitMemberRepository.delete(waitMember)
	}
}