package com.blife.blife.domain.oauth2.client.oauth2

import com.blife.blife.domain.oauth2.model.SocialMember

open class OAuth2LoginUserInfo(
	val provider: SocialMember.OAuth2Provider,
	val id: String,
	val nickname: String,
	val email: String
)