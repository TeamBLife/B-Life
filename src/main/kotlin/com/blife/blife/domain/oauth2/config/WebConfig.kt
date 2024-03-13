package com.blife.blife.domain.oauth2.config

import com.blife.blife.domain.oauth2.converter.OAuth2ProviderConverter
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

	override fun addFormatters(registry: FormatterRegistry) {
		registry.addConverter(OAuth2ProviderConverter())
	}
}