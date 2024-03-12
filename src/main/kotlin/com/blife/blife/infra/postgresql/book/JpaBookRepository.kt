package com.blife.blife.infra.postgresql.book

import com.blife.blife.infra.postgresql.book.entity.BookEntity
import org.springframework.data.repository.CrudRepository

interface JpaBookRepository : CrudRepository<BookEntity, Long> {
	fun findByIsbn10(isbn10: Long): BookEntity?
	fun findByIsbn13(isbn13: Long): BookEntity?
}