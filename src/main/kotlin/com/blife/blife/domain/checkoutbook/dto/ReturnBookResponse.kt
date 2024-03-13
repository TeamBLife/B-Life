package com.blife.blife.domain.checkoutbook.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class ReturnBookResponse(
	val id: Long,
	val library: String,
	val memberId: Long,
	val libBookId: Long,
	val checkoutTime: LocalDateTime,
	val returnTime: LocalDateTime,
	val dueDate: LocalDate,
)


