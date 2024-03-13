package com.blife.blife.domain.member.controller

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
	private val memberService: MemberService
) {
	@PostMapping("/signup")
	fun signup(@RequestBody @Valid signupRequest: MemberSignupRequest): ResponseEntity<MemberResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.signup(signupRequest))
	}

	@PostMapping("/login")
	fun login(@RequestBody loginRequest: MemberLoginRequest): ResponseEntity<MemberLoginResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.login(loginRequest))
	}

	@PostMapping("/signup/owner")
	fun ownerLogin(@RequestBody @Valid ownerSignupRequest: MemberOwnerSignupRequest): ResponseEntity<MemberResponse> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.ownerSignup(ownerSignupRequest))
	}

	// 소프트 딜리트 후 회원 탈ㅊ퇴 but 3일 이내에 다시 회월 탈퇴 철회를 하면은 바로 복귀
	@PutMapping("/{memberId}/signout")
	fun signout(
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<Unit> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.signout(userPrincipal.id))

	}
}