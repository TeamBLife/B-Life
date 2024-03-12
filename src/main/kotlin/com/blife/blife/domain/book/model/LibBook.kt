package com.blife.blife.domain.book.model

import com.blife.blife.domain.library.model.Library
import jakarta.persistence.*

@Entity
class LibBook(
	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "lib_id")
	val lib: Library,

	@ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "book_id")
	val book: Book,

	val loanAvailable: Boolean
) {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	val id: Long? = null
}