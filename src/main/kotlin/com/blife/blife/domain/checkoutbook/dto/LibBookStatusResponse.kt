package com.blife.blife.domain.checkoutbook.dto


data class LibBookStatusResponse(
    val libBook: Long,
    val copyCount: Short,
    val checkoutCount: Short,
    var loanAvailable: Boolean
)
