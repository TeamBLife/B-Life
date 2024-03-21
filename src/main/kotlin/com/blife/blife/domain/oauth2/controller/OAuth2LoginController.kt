package com.blife.blife.domain.oauth2.controller

import com.blife.blife.domain.member.dto.response.MemberLoginResponse
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2ClientService
import com.blife.blife.domain.oauth2.service.OAuth2LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OAuth2LoginController(
	private val oAuth2LoginService: OAuth2LoginService,
	private val oAuth2ClientService: OAuth2ClientService
) {

	//1. 로그인 페이지로 Redirect 해주는 API
	@GetMapping("/oauth2/login/{provider}")
	fun redirectLoginPage(
		@PathVariable provider: Member.OAuth2Provider,
		response: HttpServletResponse
	) {
		val loginPageUrl = oAuth2ClientService.generateLoginPageUrl(provider)
		response.sendRedirect(loginPageUrl)
	}

	//2. AuthorizationCode를 이용해 사용자 인증을 처리해 주는 API
	@GetMapping("/oauth2/callback/{provider}")
	fun callback(
		@PathVariable provider: Member.OAuth2Provider,
		@RequestParam(name = "code")
		authorizationCode: String
	): MemberLoginResponse {
		return oAuth2LoginService.login(provider, authorizationCode)

	}
}