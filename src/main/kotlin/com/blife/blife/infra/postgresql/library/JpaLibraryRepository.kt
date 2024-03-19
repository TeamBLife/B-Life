package com.blife.blife.infra.postgresql.library

import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import org.springframework.data.repository.CrudRepository

interface JpaLibraryRepository : CrudRepository<LibraryEntity, Long> {
	fun findByMemberId(memberId: Long): LibraryEntity?
}