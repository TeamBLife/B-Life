package com.blife.blife.global.config

import com.blife.blife.global.security.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Order(1)
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
	private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

	@Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		return http
			.httpBasic { it.disable() }
			.formLogin { it.disable() }
			.csrf { it.disable() }
			.cors { it.configurationSource(corsConfigSource()) }
			.authorizeHttpRequests {
				it.requestMatchers(
					"/oauth2/**",
					"/auth/**",
					"swagger-ui/**",
					"/v3/api-docs/**",
					"/error",
					"/books/**",
					"/libraries",
					"/libraries/search"
				).permitAll()
					.anyRequest().authenticated()
			}
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
//			.exceptionHandling{
//				it.authenticationEntryPoint(authenticationEntrypoint)
//				it.accessDeniedHandler(accessDeniedHandler)
//			}
			.build()
	}

	@Bean
	fun corsConfigSource(): CorsConfigurationSource =
		CorsConfiguration().also {
			it.allowedOrigins = listOf("*")
			it.allowedHeaders = listOf("*")
			it.allowedMethods = listOf("*")
			it.allowCredentials = false

		}.let { corsConfig ->
			UrlBasedCorsConfigurationSource().also { it.registerCorsConfiguration("/**", corsConfig) }
		}
}