package com.blife.blife.infra.postgresql.library

import com.blife.blife.infra.postgresql.book.entity.BookEntity
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import org.springframework.data.repository.CrudRepository

interface JpaLibBookRepository : CrudRepository<LibBookEntity, Long>{
	fun findByBook(bookEntity: BookEntity): LibBookEntity?
}