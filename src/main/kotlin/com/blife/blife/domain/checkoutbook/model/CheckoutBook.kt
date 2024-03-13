package com.blife.blife.domain.checkoutbook.model

import com.blife.blife.domain.member.model.Member
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class CheckoutBook(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libBook_id")
    val libBook: LibBookEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @Column(name = "returned")
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
        checkoutTime = LocalDateTime.now()
    }
}