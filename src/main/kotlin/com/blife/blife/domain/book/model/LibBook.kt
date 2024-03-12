package com.blife.blife.domain.book.model

import com.blife.blife.domain.checkoutbook.dto.LibBookStatusResponse
import com.blife.blife.domain.library.model.Library
import com.blife.blife.event.LoanAvailableEvent
import com.blife.blife.event.LoanAvailableEventListener
import com.blife.blife.infra.postgresql.book.BookEntity
import jakarta.persistence.*
import org.springframework.context.ApplicationEventPublisher


@EntityListeners(LoanAvailableEventListener::class)
@Entity
class LibBook(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lib_id")
    val lib: Library,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: BookEntity,

    @Column(name = "copy_count")
    var bookQuantity: Short,

    @Column(name = "Checkout_Count")
    var checkoutCount: Short,

    @Column(name = "loanAvailable")
    var loanAvailable: Boolean = false,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun updateLoanAvailable(newValue: Boolean) {
        this.loanAvailable = newValue
    }
}
