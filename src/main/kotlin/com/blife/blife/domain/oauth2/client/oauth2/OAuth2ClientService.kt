package com.blife.blife.domain.oauth2.client.oauth2

import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.stereotype.Component

@Component
class OAuth2ClientService(
    private val clients: List<OAuth2Client>
) {

    fun login(provider: SocialMember.OAuth2Provider, authorizationCode: String): OAuth2LoginUserInfo {
        val client: OAuth2Client = this.selectClient(provider)
        return client.getAccessToken(authorizationCode)
            .let{ client.retrieveUserInfo(it) }
    }

    private fun selectClient(provider: SocialMember.OAuth2Provider): OAuth2Client {
        return clients.find{ it.supports(provider) }
            ?: throw RuntimeException("지원하지 않는 OAuth Provider 입니다")
    }
}