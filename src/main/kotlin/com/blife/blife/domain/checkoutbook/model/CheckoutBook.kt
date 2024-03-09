package com.blife.blife.domain.checkoutbook.model

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class CheckoutBook(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libBook_id")
    val libBook: LibBook,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @Column(name = "isReturned")
    var returned : Boolean,

    @Column(name = "checkout_time" , updatable = false)
    var checkoutTime: LocalDateTime? =null,

    @Column(name = "return_time")
    var returnTime : LocalDateTime?,

    @Column(name = "due_date")
    val dueDate : LocalDate

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @PrePersist
    fun onPrePersist() {
        checkoutTime = LocalDateTime.now()  // 영속화되기 전 현재 시간으로 설정
    }
}