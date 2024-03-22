package com.blife.blife.infra.postgresql.library.util

import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.member.model.Member
import com.blife.blife.infra.postgresql.book.entity.BookEntity
import com.blife.blife.infra.postgresql.book.util.BookConvertUtil
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity

open class LibraryConvertUtil : BookConvertUtil() {
	protected fun toLibraryEntity(member: Member, lib: Library): LibraryEntity = LibraryEntity(
		member = member,
		operatingTime = lib.operatingTime,
		longitude = lib.longitude,
		tel = lib.tel,
		libId = lib.libId,
		closed = lib.closed,
		region = lib.region,
		homepage = lib.homepage,
		latitude = lib.latitude,
		address = lib.address,
		libName = lib.libName
	)

	protected fun toLibrary(libraryEntity: LibraryEntity): Library = Library(
		libId = libraryEntity.libId,
		homepage = libraryEntity.homepage,
		latitude = libraryEntity.latitude,
		address = libraryEntity.address,
		libName = libraryEntity.libName,
		region = libraryEntity.region,
		closed = libraryEntity.closed,
		operatingTime = libraryEntity.operatingTime,
		longitude = libraryEntity.longitude,
		tel = libraryEntity.tel,
		memberId = libraryEntity.member.id!!
	)

	protected fun toLibBookEntity(
		libraryEntity: LibraryEntity,
		bookEntity: BookEntity,
		libBook: LibBook
	): LibBookEntity =
		LibBookEntity(
			lib = libraryEntity,
			book = bookEntity,
			totalBookCount = libBook.totalBookCount,
			id = libBook.id
		)

	protected fun toLibBook(libBookEntity: LibBookEntity): LibBook = LibBook(
		lib = toLibrary(libBookEntity.lib),
		book = toBook(libBookEntity.book),
		totalBookCount = libBookEntity.totalBookCount,
		id = libBookEntity.id!!
	)


}