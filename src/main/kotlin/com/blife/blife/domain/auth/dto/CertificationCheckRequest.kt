package com.blife.blife.domain.auth.dto

data class CertificationCheckRequest(
	val email: String,
	val code: Long
)