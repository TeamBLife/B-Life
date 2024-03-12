package com.blife.blife.infra.postgresql.book

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.repository.IBookRepository
import com.blife.blife.infra.postgresql.book.util.BookConvertUtil
import org.springframework.stereotype.Repository

@Repository
class PostgresqlBookRepository(
	private val jpaBookRepository: JpaBookRepository,
	private val queryDslBookRepository: QueryDslBookRepository
) : IBookRepository, BookConvertUtil() {

	override fun saveBook(book: Book): Book =
		toEntity(book)
			.let { jpaBookRepository.save(it) }
			.let { toBook(it) }


	override fun searchBookByTitle(title: String, page: Int): List<Book> =
		queryDslBookRepository.searchBookBy(title, page).map { toBook(it) }

	override fun getBookByIsbn13(isbn13: Long): Book? =
		jpaBookRepository.findByIsbn13(isbn13)?.let { toBook(it) }


	override fun getBookByIsbn10(isbn10: Long): Book? =
		jpaBookRepository.findByIsbn10(isbn10)?.let { toBook(it) }

}