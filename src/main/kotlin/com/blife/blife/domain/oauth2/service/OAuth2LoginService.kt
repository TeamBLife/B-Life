package com.blife.blife.domain.oauth2.service

import com.blife.blife.domain.oauth2.JwtHelper
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2Client
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2ClientService
import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.stereotype.Service

@Service
class OAuth2LoginService(
    private val oAuth2ClientService: OAuth2ClientService,
    private val socialMemberService: SocialMemberService,
    private val jwtHelper: JwtHelper
) {
    // 1. 인가코드 -> 엑세스 토큰 발급
    // 2. 엑세스 토큰으로 사용자 정보 조회
        // 1, 2번은 하나의 과정으로 그냥 "소셜 로그인" 이라고 생각할 수도 있습니다.
    // 3. 사용자 정보로 SocialMember 있으면 조회 없으면 회원가입
    // 4. SocialMember 를 토대로 우리쪽 액세스 토큰 발급 후 응답

    fun login(provider: SocialMember.OAuth2Provider, authorizationCode: String): String {
        return oAuth2ClientService.login(provider, authorizationCode)
            .let { socialMemberService.registerIfAbsent(it) }
            .let { jwtHelper.generateAccessToken(it.id!!) }
    }
}