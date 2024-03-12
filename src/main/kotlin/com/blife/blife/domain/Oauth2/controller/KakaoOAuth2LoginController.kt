package com.blife.blife.domain.Oauth2.controller

import com.blife.blife.domain.Oauth2.client.oauth2.kakao.KakaoOAuth2Client
import com.blife.blife.domain.Oauth2.service.KakaoOAuth2LoginService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KakaoOAuth2LoginController(
    private val kakaoOAuth2LoginService: KakaoOAuth2LoginService,
    private val kakaoOAuth2Client: KakaoOAuth2Client
) {

    //1. 로그인 페이지로 Redirect 해주는 API
    @GetMapping("/oauth2/login/kakao")
    fun redirectLoginPage(response: HttpServletResponse) {
       val loginPageUrl =  kakaoOAuth2Client.generateLoginPageUrl()
        response.sendRedirect(loginPageUrl)
    }

    //2. AuthorizationCode를 이용해 사용자 인증을 처리해 주는 API
    @GetMapping("/oauth2/callback/kakao")
    fun callback(@RequestParam(name = "code") authorizationCode: String): String {
        return kakaoOAuth2LoginService.login(authorizationCode)

    }
}