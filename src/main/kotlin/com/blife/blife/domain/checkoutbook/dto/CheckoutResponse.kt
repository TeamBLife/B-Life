package com.blife.blife.domain.checkoutbook.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class CheckoutResponse (
    val id : Long,
    val memberId : Long,
    val libBookId : Long,
    val checkoutTime : LocalDateTime,
    val dueDate : LocalDate
    )


