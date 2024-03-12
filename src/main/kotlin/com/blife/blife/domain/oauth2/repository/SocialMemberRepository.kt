package com.blife.blife.domain.oauth2.repository

import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SocialMemberRepository : CrudRepository<SocialMember, Long> {
    fun existsByProviderAndProviderId(kakao: SocialMember.OAuth2Provider, toString: String): Boolean
    fun findByProviderAndProviderId(kakao: SocialMember.OAuth2Provider, toString: String): SocialMember


}