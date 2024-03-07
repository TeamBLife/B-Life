package com.blife.blife.infra.postgresql.book

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.repository.IBookRepository
import org.springframework.stereotype.Repository

@Repository
class PostgresqlBookRepository(
	private val jpaBookRepository: JpaBookRepository,
	private val queryDslBookRepository: QueryDslBookRepository
) : IBookRepository {

	private fun toEntity(book: Book) = BookEntity(
		description = book.description,
		isbn13 = book.isbn13,
		isbn10 = book.isbn10,
		publicationDate = book.publicationDate,
		author = book.author,
		coverUrl = book.coverUrl,
		bookName = book.title
	)

	private fun toBook(entity: BookEntity) = Book(
		title = entity.bookName,
		description = entity.description,
		isbn13 = entity.isbn13,
		isbn10 = entity.isbn10,
		publicationDate = entity.publicationDate,
		author = entity.author,
		coverUrl = entity.coverUrl
	)

	override fun saveBook(book: Book): Book =
		toEntity(book)
			.let { jpaBookRepository.save(it) }
			.let { toBook(it) }


	override fun searchBookByTitle(title: String, page: Int): List<Book> =
		queryDslBookRepository.searchBookBy(title, page).map { toBook(it) }

	override fun getBookByIsbn13(isbn13: Long): Book =
		jpaBookRepository.findByIsbn13(isbn13)?.let { toBook(it) } ?: throw TODO("에러")


	override fun getBookByIsbn10(isbn10: Long): Book =
		jpaBookRepository.findByIsbn10(isbn10)?.let { toBook(it) } ?: throw TODO("에러")

}