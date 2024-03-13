package com.blife.blife.domain.checkoutbook.dto


data class LibBookStatusResponse(
    val libBookName: String,
    val bookQuantity: Long,
    val checkoutCount: Long,
    var loanAvailable: Boolean
)
