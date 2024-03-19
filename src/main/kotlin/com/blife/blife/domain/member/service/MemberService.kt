package com.blife.blife.domain.member.service

import com.blife.blife.domain.member.dto.request.MemberLoginRequest
import com.blife.blife.domain.member.dto.request.MemberSignupRequest
import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.dto.response.MemberResponse
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.model.WaitMember
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.member.repository.WaitMemberRepository
import com.blife.blife.global.exception.CustomException
import com.blife.blife.global.exception.InvalidCredentialException
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
		memberRepository.findByEmail(request.email)?.let { throw TODO("이미 가입되어 있는 Email") }
		return memberRepository.save(
			Member(
				email = request.email,
				name = request.name,
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
			.let { member -> MemberResponse(member.role, member.name, member.email) }
	}

	fun login(request: MemberLoginRequest): MemberLoginResponse {
		val user = memberRepository.findByEmail(request.email) ?: throw IllegalArgumentException("member")

		if (!passwordEncoder.matches(request.password, user.password)) {
			throw InvalidCredentialException("Email is already in use")
		}

		return MemberLoginResponse(
			accessToken = jwtPlugin.generateAccessToken(
				subject = user.id.toString(),
				email = user.email,
				role = user.role.name
			)
		)
	}

	fun signout(id: Long) {
		val member = memberRepository.findByIdOrNull(id) ?: throw Exception("")

		when (member.role) {
			MemberRole.USER -> {
				member.name = "탈퇴한 회원${member.id}"
				member.isDeleted = true
			}

			MemberRole.OWNER -> {
				member.name = "탈퇴한 회원${member.id}"
				member.isDeleted = true
			}

			MemberRole.ADMIN -> throw IllegalArgumentException("Admin cannot whitdraw")
			else -> throw IllegalArgumentException("Invalid role")
		}
	}
}
