package com.blife.blife.domain.oauth2.client.oauth2.naver

import com.blife.blife.domain.oauth2.client.oauth2.OAuth2Client
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2LoginUserInfo
import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.stereotype.Component

@Component
class NaverOAuth2Client: OAuth2Client {
    override fun generateLoginPageUrl(): String {
        TODO("Not yet implemented")
    }

    override fun getAccessToken(authorizationCode: String): String {
        TODO("Not yet implemented")
    }

    override fun retrieveUserInfo(accessToken: String): OAuth2LoginUserInfo {
        TODO("Not yet implemented")
    }

    override fun supports(provider: SocialMember.OAuth2Provider): Boolean {
        return provider == SocialMember.OAuth2Provider.NAVER
    }
}