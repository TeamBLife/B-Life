package com.blife.blife.domain.oauth2.service

import com.blife.blife.domain.oauth2.client.oauth2.OAuth2LoginUserInfo
import com.blife.blife.domain.oauth2.model.SocialMember
import com.blife.blife.domain.oauth2.repository.SocialMemberRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
    private val socialMemberRepository: SocialMemberRepository
) {
    fun registerIfAbsent(userInfo: OAuth2LoginUserInfo): SocialMember {
        return if (!socialMemberRepository.existsByProviderAndProviderId(userInfo.provider, userInfo.id)) {
            val socialMember = SocialMember(
                provider = userInfo.provider,
                providerId = userInfo.id,
                nickname = userInfo.nickname,
                email = userInfo.email
            )
            socialMemberRepository.save(socialMember)
        } else {
            socialMemberRepository.findByProviderAndProviderId(userInfo.provider, userInfo.id)
        }
    }
}