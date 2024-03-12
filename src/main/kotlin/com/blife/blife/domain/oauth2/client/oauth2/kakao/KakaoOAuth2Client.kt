package com.blife.blife.domain.oauth2.client.oauth2.kakao

import com.blife.blife.domain.oauth2.client.oauth2.OAuth2Client
import com.blife.blife.domain.oauth2.client.oauth2.kakao.dto.KakaoTokenResponse
import com.blife.blife.domain.oauth2.client.oauth2.kakao.dto.KakaoUserInfoResponse
import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Component
class KakaoOAuth2Client(
    @Value("24145d59fd63241d2791c882bee45c8a") val clientId: String,
    @Value("http://localhost:8080/oauth2/kakao") val redirectUrl: String,
    private val restClient: RestClient
): OAuth2Client {
    override fun generateLoginPageUrl(): String {
        return StringBuilder(KAKAO_AUTH_BASE_URL)
            .append("/oauth/authorize")
            .append("?client_id=").append(clientId)
            .append("&redirect_uri=").append(redirectUrl)
            .append("&response_type=").append("code")
            .toString()
    }

    override fun getAccessToken(authorizationCode: String): String {
        val requestData = mutableMapOf(
            "grant_type" to "authorization_code",
            "client_id" to clientId,
            "code" to authorizationCode
        )
        return restClient.post()
            .uri("$KAKAO_AUTH_BASE_URL/oauth/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(LinkedMultiValueMap<String, String>().apply { this.setAll(requestData) })
            .retrieve()
            .body<KakaoTokenResponse>()
            ?.accessToken
            ?: throw RuntimeException("AccessToken 조회 실패")
    }

    override fun retrieveUserInfo(accessToken: String): KakaoUserInfoResponse {
        return restClient.get()
            .uri("$KAKAO_API_BASE_URL/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .body<KakaoUserInfoResponse>()
            ?: throw RuntimeException("UserInfo 조회 실패")
    }

    override fun supports(provider: SocialMember.OAuth2Provider): Boolean {
        return provider == SocialMember.OAuth2Provider.KAKAO
    }

    companion object {
        private const val KAKAO_AUTH_BASE_URL = "https://kauth.kakao.com"
        private const val KAKAO_API_BASE_URL = "https://kapi.kakao.com"
    }


}