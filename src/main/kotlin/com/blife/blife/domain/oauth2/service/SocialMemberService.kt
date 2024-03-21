package com.blife.blife.domain.oauth2.service

import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.oauth2.client.oauth2.OAuth2LoginUserInfo
import org.springframework.stereotype.Service

@Service
class SocialMemberService(
	private val memberRepository: MemberRepository

) {
	fun registerIfAbsent(userInfo: OAuth2LoginUserInfo): Member {
		return if (memberRepository.existsByProviderAndProviderId(userInfo.provider, userInfo.id)) {

			val socialMember = Member(
				isSocialUser =  true,
				provider = userInfo.provider,
				providerId = userInfo.id,
				nickname = userInfo.nickname,
				email = userInfo.email,
				role = MemberRole.USER,
			)

			memberRepository.save(socialMember)
		} else {
			memberRepository.findByProviderAndProviderId(userInfo.provider, userInfo.id)
		}
	}
}