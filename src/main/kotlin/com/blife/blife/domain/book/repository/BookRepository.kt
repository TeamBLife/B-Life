package com.blife.blife.domain.book.repository

import com.blife.blife.domain.book.model.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

}