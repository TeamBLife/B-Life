package com.blife.blife.infra.postgresql.library.entity

import com.blife.blife.infra.postgresql.book.entity.BookEntity
import jakarta.persistence.*

@Entity
@Table(name = "lib_book")
class LibBookEntity(
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lib_id")
	val lib: LibraryEntity,

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	val book: BookEntity,

	val totalBookCount: Long
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}