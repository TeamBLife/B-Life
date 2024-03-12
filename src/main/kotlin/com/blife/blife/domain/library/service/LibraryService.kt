package com.blife.blife.domain.library.service

import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.library.repository.ILibraryRepository
import com.blife.blife.infra.postgresql.library.PostgresqlLibraryRepository
import org.springframework.stereotype.Service

@Service
class LibraryService(
	private val libraryRepository: ILibraryRepository
) {
	fun addLib(lib: Library): Library =
		libraryRepository.addLibrary(lib)

	fun addLibBook(libBook: LibBook) {}

	fun getLibBook(id: Long): LibBook {
		return 1 as LibBook
	}

	fun getLibrary(libId: Long): Library {
		return 1 as Library
	}
}