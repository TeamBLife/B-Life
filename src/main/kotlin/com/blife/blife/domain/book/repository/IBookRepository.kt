package com.blife.blife.domain.book.repository

import com.blife.blife.domain.book.model.Book

interface IBookRepository {
	fun saveBook(book: Book): Book
	fun searchBookByTitle(title: String, page: Long): List<Book>
	fun getBookByIsbn13(isbn13: Long): Book?
	fun getBookByIsbn10(isbn10: Long): Book?
}