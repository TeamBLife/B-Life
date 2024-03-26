package com.blife.blife.domain.auth.controller

import com.blife.blife.domain.auth.dto.CertificationCheckRequest
import com.blife.blife.domain.auth.service.AuthService
import com.blife.blife.domain.member.dto.request.MemberLoginRequest
import com.blife.blife.domain.member.dto.request.MemberSignupRequest
import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.dto.response.MemberResponse
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.service.MemberService
import com.blife.blife.global.security.UserPrincipal
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
	private val memberService: MemberService,
	private val authService: AuthService
) {
	@PostMapping("/check")
	fun memberCheckCertification(@RequestBody request: CertificationCheckRequest): ResponseEntity<String> {
		authService.checkCertification(request)
		return ResponseEntity
			.status(HttpStatus.OK)
			.body("Success")
	}

	@PostMapping("/signup")
	fun signup(@RequestBody @Valid signupRequest: MemberSignupRequest, @RequestParam role: MemberRole): ResponseEntity<MemberResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.signup(signupRequest, role))
	}

	@PostMapping("/login")
	fun login(@RequestBody loginRequest: MemberLoginRequest): ResponseEntity<MemberLoginResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.login(loginRequest))
	}


	// 소프트 딜리트 후 회원 탈ㅊ퇴 but 3일 이내에 다시 회월 탈퇴 철회를 하면은 바로 복귀

}