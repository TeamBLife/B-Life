package com.blife.blife.domain.book.model

import com.blife.blife.domain.library.model.Library
import com.blife.blife.infra.postgresql.book.BookEntity
import jakarta.persistence.*

@Entity
class LibBook(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lib_id")
    val lib: Library,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: BookEntity,

    @Column(name = "copy_count")
    var copyCount: Short,

    @Column(name = "Checkout_Count")
    var checkoutCount: Short,

    var loanAvailable: Boolean

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}