package com.blife.blife.domain.Oauth2.service

import com.blife.blife.domain.Oauth2.client.oauth2.kakao.dto.KakaoUserInfoResponse
import com.blife.blife.domain.Oauth2.model.SocialMember
import com.blife.blife.domain.Oauth2.repository.SocialMemberRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
    private val socialMemberRepository: SocialMemberRepository
) {
    fun registerIfAbsent(userInfo: KakaoUserInfoResponse): SocialMember {
        return if (!socialMemberRepository.existsByProviderAndProviderId(SocialMember.OAuth2Provider.KAKAO, userInfo.id.toString())) {
            val socialMember = SocialMember.ofKakao(userInfo.id, userInfo.nickname, userInfo.email)
            socialMemberRepository.save(socialMember)
        } else {
            socialMemberRepository.findByProviderAndProviderId(SocialMember.OAuth2Provider.KAKAO, userInfo.id.toString())
        }
    }
}