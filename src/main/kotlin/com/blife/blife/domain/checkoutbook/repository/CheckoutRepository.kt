package com.blife.blife.domain.checkoutbook.repository

import com.blife.blife.domain.checkoutbook.model.CheckoutBook
import com.blife.blife.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutRepository : JpaRepository< CheckoutBook, Long> {
    fun countByMemberIdAndReturnedFalse(memberId: Long): Int

    fun findByMemberIdAndLibBookIdAndReturnedFalse(memberId: Long, libBookId : Long) : CheckoutBook?
}