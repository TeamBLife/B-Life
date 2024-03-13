package com.blife.blife.domain.oauth2.converter

import com.blife.blife.domain.oauth2.model.SocialMember
import org.springframework.core.convert.converter.Converter

class OAuth2ProviderConverter : Converter<String, SocialMember.OAuth2Provider> {
	override fun convert(source: String): SocialMember.OAuth2Provider {
		return runCatching {
			SocialMember.OAuth2Provider.valueOf(source.uppercase())
		}.getOrElse {
			throw IllegalArgumentException(it)
		}
	}


}