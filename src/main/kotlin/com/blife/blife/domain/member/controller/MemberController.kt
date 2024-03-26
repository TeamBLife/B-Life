package com.blife.blife.domain.member.controller

import com.blife.blife.domain.member.service.MemberService
import com.blife.blife.global.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(
	private val memberService: MemberService,
) {
	@PutMapping("/signout")
	fun signout(
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<Unit> {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(memberService.signout(userPrincipal.id))
	}
}