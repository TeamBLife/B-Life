package com.blife.blife.domain.member.service

import com.blife.blife.domain.member.dto.request.MemberLoginRequest
import com.blife.blife.domain.member.dto.request.MemberSignupRequest
import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.dto.response.MemberResponse
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.enums.MemberStatus
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.model.WaitMember
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.member.repository.WaitMemberRepository
import com.blife.blife.global.exception.*
import com.blife.blife.global.security.JwtPlugin
import com.blife.blife.global.util.mail.service.MemberMailService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
	private val memberRepository: MemberRepository,
	private val passwordEncoder: PasswordEncoder,
	private val memberMailService: MemberMailService,
	private val waitMemberRepository: WaitMemberRepository,
	private val jwtPlugin: JwtPlugin
) {
	@Transactional
	fun signup(request: MemberSignupRequest, role: MemberRole): MemberResponse {
		memberRepository.findByEmail(request.email)?.let { throw ModelNotFoundException(ErrorCode.EMAIL_ALREADY_REGISTERED) }
		return memberRepository.save(
			Member(
				isSocialUser = false,
				providerId = null,
				email = request.email,
				nickname = request.nickname,
				password = passwordEncoder.encode(request.password),
				role = role
			)
		)
			.also { member ->
				val code = memberMailService.memberSendMail(member.email)
				waitMemberRepository.save(
					WaitMember(
						email = member.email,
						code = code
					)
				)
			}
			.let { member ->
				MemberResponse(
					role = member.role,
					nickname = member.nickname,
					email = member.email,
					status = member.status
				)
			}
	}

	fun login(request: MemberLoginRequest): MemberLoginResponse {
		val user = memberRepository.findByEmail(request.email) ?: throw ModelNotFoundException(ErrorCode.MEMBER_NOT_FOUND)

		if (user.isSocialUser) {
			throw ForbiddenException(ErrorCode.SOCIAL_MEMBER_CANNOT_LOGIN)
		}

		if (user.status == MemberStatus.WAIT) {
			throw ForbiddenException(ErrorCode.AUTHENTICATE_NOT_YET)
		}

		if (!passwordEncoder.matches(request.password, user.password)) {
			throw BadRequestException(ErrorCode.WRONG_PASSWORD)
		}

		return MemberLoginResponse(
			accessToken = jwtPlugin.generateAccessToken(
				subject = user.id.toString(),
				email = user.email,
				role = user.role.name
			),
			nickname = user.nickname,
			status = user.status
		)
	}

	fun signout(id: Long) {
		val member = memberRepository.findByIdOrNull(id) ?: throw Exception("")

		when (member.role) {
			MemberRole.USER -> {
				member.nickname = "탈퇴한 회원${member.id}"
				member.isDeleted = true
			}

			MemberRole.OWNER -> {
				member.nickname = "탈퇴한 회원${member.id}"
				member.isDeleted = true
			}

			MemberRole.ADMIN -> throw ForbiddenException(ErrorCode.ADMINISTRATORS_CANNOT_WITHDRAW)
			else -> throw BadRequestException(ErrorCode.WRONG_ROLE)
		}
	}
}
