package com.blife.blife.domain.book.model

import com.blife.blife.domain.library.model.Library
import com.blife.blife.infra.postgresql.book.BookEntity
import jakarta.persistence.*


@Entity
class LibBook(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lib_id")
    val library: Library,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: BookEntity,

    @Column(name = "book_quantity")
    var bookQuantity: Long,


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}