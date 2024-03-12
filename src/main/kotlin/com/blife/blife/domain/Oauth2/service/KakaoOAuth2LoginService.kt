package com.blife.blife.domain.Oauth2.service

import com.blife.blife.domain.Oauth2.JwtHelper
import com.blife.blife.domain.Oauth2.client.oauth2.kakao.KakaoOAuth2Client
import org.springframework.stereotype.Service

@Service
class KakaoOAuth2LoginService(
    private val kakaoOAuth2Client: KakaoOAuth2Client,
    private val socialMemberService: SocialMemberService,
    private val jwtHelper: JwtHelper
) {
    // 1. 인가코드 -> 엑세스 토큰 발급
    // 2. 엑세스 토큰으로 사용자 정보 조회
        // 1, 2번은 하나의 과정으로 그냥 "소셜 로그인" 이라고 생각할 수도 있습니다.
    // 3. 사용자 정보로 SocialMember 있으면 조회 없으면 회원가입
    // 4. SocialMember 를 토대로 우리쪽 액세스 토큰 발급 후 응답

    fun login(authorizationCode: String): String {
        return kakaoOAuth2Client.getAccessToken(authorizationCode)
            .let { kakaoOAuth2Client.retrieveUserInfo(it) }
            .let { socialMemberService.registerIfAbsent(it) }
            .let { jwtHelper.generateAccessToken(it.id!!) }
    }
}