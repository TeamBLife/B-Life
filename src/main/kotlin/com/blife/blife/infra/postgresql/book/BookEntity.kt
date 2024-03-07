package com.blife.blife.infra.postgresql.book

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class BookEntity(
	val bookName: String,
	val author: String, // NOTE: 저자가 2명 이상일 경우 [ , ]로 나누어 관리하도록 한다.

	@Column(unique = true)
	val isbn10: Long?,
	@Column(unique = true)
	val isbn13: Long?,

	val coverUrl: String,
	val description: String,
	val publicationDate: LocalDateTime, // 출판일자
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}
