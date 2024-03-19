package com.blife.blife.domain.library.model

import com.blife.blife.domain.book.model.Book

class LibBook(
	val lib: Library,
	val book: Book,
	var totalBookCount: Long,
	val id: Long? = null
) {
	companion object {
		fun of(lib: Library, book: Book, totalBookCount: Long) = LibBook(
			lib = lib,
			book = book,
			totalBookCount = totalBookCount
		)
	}
}