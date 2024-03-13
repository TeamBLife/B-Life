package com.blife.blife.infra.postgresql.book

import com.blife.blife.infra.postgresql.book.entity.BookEntity
import com.blife.blife.infra.postgresql.book.entity.QBookEntity
import com.blife.blife.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Component

@Component
class QueryDslBookRepository : QueryDslSupport() {

	private val book = QBookEntity.bookEntity

	fun searchBookBy(title: String, page: Int): List<BookEntity> =
		queryFactory
			.selectFrom(book)
			.where(
				BooleanBuilder()
					.or(book.bookName.contains(title))
			)
			.limit(10.toLong())
			.offset(((page - 1) * 10).toLong())
			.fetch()
			.toList()
}