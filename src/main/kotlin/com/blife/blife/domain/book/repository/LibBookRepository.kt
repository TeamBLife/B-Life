package com.blife.blife.domain.book.repository

import com.blife.blife.domain.book.model.LibBook
import org.springframework.data.jpa.repository.JpaRepository

interface LibBookRepository : JpaRepository<LibBook, Long> {
    fun findByBookIdAndLibId(bookId : Long, libraryId : Long) : LibBook?
}
