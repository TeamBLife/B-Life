package com.blife.blife.domain.book.service

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.repository.IBookRepository
import org.springframework.stereotype.Service

@Service
class 	BookService(
	private val bookRepository: IBookRepository
) {
	fun getBookByIsbn(isbn: Long): Book? =
		bookRepository.getBookByIsbn13(isbn)

	fun addBook(book: Book): Book =
		bookRepository.saveBook(book)

	fun searchBookListByTitle(title: String, page: Int): List<Book> =
		bookRepository.searchBookByTitle(title, page)
}