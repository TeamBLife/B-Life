package com.blife.blife.domain.member.service

import com.blife.blife.domain.member.dto.request.MemberLoginRequest
import com.blife.blife.domain.member.dto.request.MemberOwnerSignupRequest
import com.blife.blife.domain.member.dto.request.MemberSignupRequest
import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.dto.response.MemberResponse
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.global.exception.InvalidCredentialException
import com.blife.blife.global.security.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
	private val memberRepository: MemberRepository,
	private val passwordEncoder: PasswordEncoder,
	private val jwtPlugin: JwtPlugin
) {
	fun signup(request: MemberSignupRequest): MemberResponse {
		return memberRepository.save(
			Member(
				email = request.email,
				name = request.name,
				password = passwordEncoder.encode(request.password),
				role = MemberRole.USER
			)
		).let { MemberResponse(it.role, it.name, it.email) }
	}

	fun login(request: MemberLoginRequest): MemberLoginResponse {
		val user = memberRepository.findByEmail(request.email) ?: throw IllegalArgumentException("member")

		if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) {
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

	fun ownerSignup(request: MemberOwnerSignupRequest): MemberResponse {
		return memberRepository.save(
			Member(
				email = request.email,
				name = request.name,
				password = passwordEncoder.encode(request.password),
				role = MemberRole.OWNER
			)
		).let { MemberResponse(it.role, it.name, it.email) }
	}

	fun signout(id: Long) {
		val member = memberRepository.findByIdOrNull(id) ?: throw Exception("")

		when (member.role) {
			MemberRole.USER -> {
				member.name = "탈퇴한 회원${member.id}"
				member.role = MemberRole.WITHDRAWN
				member.isDeleted = true
			}

			MemberRole.OWNER -> {
				member.name = "탈퇴한 회원${member.id}"
				member.role = MemberRole.WITHDRAWN
				member.isDeleted = true
			}

			MemberRole.ADMIN -> throw IllegalArgumentException("Admin cannot whitdraw")
			else -> throw IllegalArgumentException("Invalid role")
		}
	}
}
