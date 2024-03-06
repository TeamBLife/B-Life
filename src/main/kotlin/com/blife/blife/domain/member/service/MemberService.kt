package com.blife.blife.domain.member.service

import com.blife.blife.domain.member.dto.MemberLoginResponse
import com.blife.blife.domain.member.dto.MemberLoginRequest
import com.blife.blife.domain.member.dto.MemberRespose
import com.blife.blife.domain.member.dto.MemberSignupRequest
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.global.exception.InvalidCredentialException
import com.blife.blife.global.security.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
){
    fun signup(request: MemberSignupRequest): MemberRespose {
        return memberRepository.save(
            Member(
                email = request.email,
                name = request.name,
                password = passwordEncoder.encode(request.password),
                role = when (request.role) {
                    "USER" -> MemberRole.USER
                    "OWNER" -> MemberRole.OWNER
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).let { MemberRespose(it.role, it.name, it.email) }
    }

    fun login(request: MemberLoginRequest): MemberLoginResponse {
        val user = memberRepository.findByEmail(request.email) ?: throw IllegalArgumentException("member")

        if(user.role.name != request.role || !passwordEncoder.matches(request.password, user.password )) {
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
}