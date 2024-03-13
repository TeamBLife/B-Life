package com.blife.blife.domain.member.controller

import com.blife.blife.global.util.mail.service.MemberMailService
import com.blife.blife.domain.member.dto.request.MemberLoginRequest
import com.blife.blife.domain.member.dto.request.MemberOwnerSignupRequest
import com.blife.blife.domain.member.dto.request.MemberSignupRequest
import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.dto.response.MemberResponse
import com.blife.blife.domain.member.service.MemberService
import com.blife.blife.global.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
	private val memberService: MemberService,
	private val memberMailService: MemberMailService
) {


}