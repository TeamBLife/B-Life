package com.blife.blife.domain.book.model

import com.blife.blife.domain.library.model.Library
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Book(
    val bookName: String,
    val author: String, // NOTE: 저자가 2명 이상일 경우 [ , ]로 나누어 관리하도록 한다.
    val isbn10: Long?,
    val isbn13: Long?,
    val coverUrl: String,
    val description: String,
    val publicationYear: LocalDateTime, // 출판년도
    val publicationDate: LocalDateTime, // 출판일자
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
