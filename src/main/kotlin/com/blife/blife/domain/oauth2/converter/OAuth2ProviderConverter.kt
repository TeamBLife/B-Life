package com.blife.blife.domain.oauth2.converter

import com.blife.blife.domain.member.model.Member
import org.springframework.core.convert.converter.Converter

class OAuth2ProviderConverter : Converter<String, Member.OAuth2Provider> {
	override fun convert(source: String): Member.OAuth2Provider {
		return runCatching {
			Member.OAuth2Provider.valueOf(source.uppercase())
		}.getOrElse {
			throw IllegalArgumentException(it)
		}
	}


}