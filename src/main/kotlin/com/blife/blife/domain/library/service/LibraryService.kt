package com.blife.blife.domain.library.service

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library
import com.blife.blife.domain.library.repository.ILibraryRepository
import org.springframework.stereotype.Service

@Service
class LibraryService(
	private val libraryRepository: ILibraryRepository
) {
	fun saveLib(lib: Library): Library = libraryRepository.saveLibrary(lib)
	fun getLibrary(libId: Long): Library = libraryRepository.getLibraryByLibId(libId)
	fun searchLibrary(regionCode: Long? = null, libName: String? = null, page: Long, pageSize: Long): List<Library> {
		val region = regionCode?.let { REGION.getByCode(it) }
		return libraryRepository.searchLibrary(region, libName, page, pageSize)
	}

	fun saveLibBook(libBook: LibBook): LibBook = libraryRepository.saveLibBook(libBook)
	fun getLibBook(libBookId: Long): LibBook = libraryRepository.getLibBookById(libBookId)
	fun deleteLibBook(libBookId: Long): Unit = libraryRepository.removeLibBook(libBookId)

	fun checkLibBookOwner(libBookId: Long, ownerId: Long): Boolean =
		libraryRepository.isLibBookOwner(libBookId, ownerId)
			.apply { if (!this) throw TODO("Owner 아님") }
}