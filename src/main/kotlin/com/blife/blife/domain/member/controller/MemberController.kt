package com.blife.blife.domain.member.controller

import com.blife.blife.global.util.mail.service.MemberMailService
import com.blife.blife.domain.member.service.MemberService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
	private val memberService: MemberService,
	private val memberMailService: MemberMailService
) {


}