package com.blife.blife.infra.postgresql.library

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import com.blife.blife.infra.postgresql.library.entity.QLibraryEntity
import com.blife.blife.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import org.springframework.stereotype.Component

@Component
class QueryDslLibraryRepository : QueryDslSupport() {

	private val library = QLibraryEntity.libraryEntity

	fun searchLibrary(region: REGION?, libName: String?, page: Long, pageSize: Long): List<LibraryEntity> {
		return queryFactory
			.selectFrom(library)
			.where(
				BooleanBuilder()
					.and(equalRegion(region))
					.and(containsLibName(libName))
			)
			.limit(pageSize)
			.offset((page - 1) * pageSize)
			.fetch()
			.toList()
	}

	fun equalRegion(region: REGION?) = region?.let { library.region.eq(it) }

	fun containsLibName(libName: String?) = libName?.let { library.libName.contains(it) }
}