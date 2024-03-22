package com.blife.blife.domain.library.repository

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library

interface ILibraryRepository {
	fun saveLibrary(lib: Library): Library
	fun getLibraryByLibId(libId: Long): Library
	fun searchLibrary(region: REGION?, libName: String?, page: Long, pageSize: Long): List<Library>

	fun saveLibBook(libBook: LibBook): LibBook
	fun removeLibBook(libBookId: Long)
	fun getLibBookById(libBookId: Long): LibBook

	fun isLibBookOwner(libBookId: Long, ownerId: Long): Boolean
	fun isLibraryOwner(libId: Long, ownerId: Long): Boolean
}