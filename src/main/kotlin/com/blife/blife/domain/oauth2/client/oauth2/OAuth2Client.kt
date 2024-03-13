package com.blife.blife.domain.oauth2.client.oauth2

import com.blife.blife.domain.oauth2.model.SocialMember

interface OAuth2Client {
	fun generateLoginPageUrl(): String
	fun getAccessToken(authorizationCode: String): String
	fun retrieveUserInfo(accessToken: String): OAuth2LoginUserInfo
	fun supports(provider: SocialMember.OAuth2Provider): Boolean
}