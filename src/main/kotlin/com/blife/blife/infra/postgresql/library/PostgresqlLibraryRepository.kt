package com.blife.blife.infra.postgresql.library

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.library.repository.ILibraryRepository
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.infra.postgresql.book.JpaBookRepository
import com.blife.blife.infra.postgresql.book.entity.BookEntity
import com.blife.blife.infra.postgresql.library.entity.LibraryEntity
import com.blife.blife.infra.postgresql.library.util.LibraryConvertUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class PostgresqlLibraryRepository(
	private val jpaLibraryRepository: JpaLibraryRepository,
	private val jpaLibBookRepository: JpaLibBookRepository,
	private val jpaBookRepository: JpaBookRepository,
	private val memberRepository: MemberRepository
) : ILibraryRepository, LibraryConvertUtil() {
	private fun getBookEntity(book: Book): BookEntity {
		return jpaBookRepository.findByIsbn13(book.isbn13) ?: throw TODO("")
	}

	private fun getMember(lib: Library): Member {
		return memberRepository.findByIdOrNull(lib.memberId) ?: throw TODO("")
	}

	private fun getLibrary(lib: Library): LibraryEntity {
		return jpaLibraryRepository.findByIdOrNull(lib.libId) ?: throw TODO("")
	}

	override fun addLibrary(lib: Library): Library {
		val member = getMember(lib)
		return toLibraryEntity(member, lib)
			.let { jpaLibraryRepository.save(it) }
			.let { toLibrary(it) }
	}

	override fun addLibBook(libBook: LibBook): LibBook {
		val library = getLibrary(libBook.lib)
		val book = getBookEntity(libBook.book)

		return toLibBookEntity(library, book, libBook)
			.let { jpaLibBookRepository.save(it) }
			.let { toLibBook(it) }
	}

}