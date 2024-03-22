package com.blife.blife.domain.oauth2.client.oauth2

import com.blife.blife.domain.member.model.Member

interface OAuth2Client {
	fun generateLoginPageUrl(): String
	fun getAccessToken(authorizationCode: String): String
	fun retrieveUserInfo(accessToken: String): OAuth2LoginUserInfo
	fun supports(provider: Member.OAuth2Provider): Boolean
}