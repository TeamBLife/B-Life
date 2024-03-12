package com.blife.blife.domain.checkoutbook.dto


data class LibBookStatusResponse(
    val libBook: Long,
    val bookQuantity: Short,
    val checkoutCount: Short,
    var loanAvailable: Boolean
)
