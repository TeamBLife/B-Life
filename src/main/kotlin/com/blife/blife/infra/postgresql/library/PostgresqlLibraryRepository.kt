package com.blife.blife.infra.postgresql.library

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.library.repository.ILibraryRepository
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.infra.postgresql.book.JpaBookRepository
import com.blife.blife.infra.postgresql.library.util.LibraryConvertUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PostgresqlLibraryRepository(
	private val jpaLibraryRepository: JpaLibraryRepository,
	private val jpaLibBookRepository: JpaLibBookRepository,
	private val jpaBookRepository: JpaBookRepository,
	private val memberRepository: MemberRepository,
	private val queryDslLibraryRepository: QueryDslLibraryRepository
) : ILibraryRepository, LibraryConvertUtil() {

	@Transactional
	override fun saveLibrary(lib: Library): Library {
		val member = memberRepository.findByIdOrNull(lib.memberId) ?: throw TODO("")
		return toLibraryEntity(member, lib)
			.let { jpaLibraryRepository.save(it) }
			.let { toLibrary(it) }
	}

	@Transactional
	override fun saveLibBook(libBook: LibBook): LibBook {
		val libEntity = jpaLibraryRepository.findByIdOrNull(libBook.lib.libId) ?: throw TODO("")
		val bookEntity = jpaBookRepository.findByIsbn13(libBook.book.isbn13) ?: throw TODO("")

		return toLibBookEntity(libEntity, bookEntity, libBook)
			.let { jpaLibBookRepository.save(it) }
			.let { toLibBook(it) }
	}

	override fun getLibraryByLibId(libId: Long): Library {
		return jpaLibraryRepository.findByIdOrNull(libId)
			?.let { toLibrary(it) }
			?: throw TODO("")
	}

	override fun searchLibrary(region: REGION?, libName: String?, page: Long, pageSize: Long): List<Library> {
		return queryDslLibraryRepository.searchLibrary(region, libName, page, pageSize)
			.map { toLibrary(it) }
	}


	@Transactional
	override fun removeLibBook(libBookId: Long) {
		jpaLibBookRepository.findByIdOrNull(libBookId)
			?.let { jpaLibBookRepository.delete(it) }
			?: throw TODO("")
	}

	override fun getLibBookById(libBookId: Long): LibBook {
		return jpaLibBookRepository.findByIdOrNull(libBookId)
			?.let { toLibBook(it) }
			?: throw TODO("")
	}

	@Transactional
	override fun isLibBookOwner(libBookId: Long, ownerId: Long): Boolean {
		val libBookEntity = jpaLibBookRepository.findByIdOrNull(libBookId) ?: TODO("등록되지 않은 도서관 책")

		return libBookEntity.lib.member.id == ownerId
	}

	override fun isLibraryOwner(libId: Long, ownerId: Long): Boolean {
		val libraryEntity = jpaLibraryRepository.findByIdOrNull(libId) ?: TODO("도서관이 존재하지 않음")

		return libraryEntity.member.id!! == ownerId
	}


}