package com.blife.blife.domain.book.model

import com.blife.blife.domain.library.model.Library
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Book(
    val bookName: String,
    val author:String,
    val publishedDate: LocalDateTime,
    val isbn: Long,
    val count: Int,
    val checkOutCount: Int,
    val registeredDate: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "library_id")
    val libraryId: Library?
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
