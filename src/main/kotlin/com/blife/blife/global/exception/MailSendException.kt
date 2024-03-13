package com.blife.blife.global.exception

data class MailSendException(
	override val message: String? = "인증번호가 잘못되었거나 인증 시간이 초과되었습니다. 다시 확인해주세요."
) : RuntimeException()