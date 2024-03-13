package com.blife.blife.domain.checkoutbook.repository

import com.blife.blife.domain.checkoutbook.model.CheckoutBook
import org.springframework.data.jpa.repository.JpaRepository

interface CheckoutRepository : JpaRepository<CheckoutBook, Long> {

	fun countByLibBookIdAndReturnedFalse(libBookId: Long): Long


	fun countByMemberIdAndReturnedFalse(memberId: Long): Long

	fun findByMemberIdAndLibBookIdAndReturnedFalse(memberId: Long, libBookId: Long): CheckoutBook?
}