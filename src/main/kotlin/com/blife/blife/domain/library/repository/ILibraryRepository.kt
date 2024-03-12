package com.blife.blife.domain.library.repository

import com.blife.blife.domain.library.model.LibBook
import com.blife.blife.domain.library.model.Library

interface ILibraryRepository {
	fun addLibrary(lib: Library): Library
	fun addLibBook(libBook: LibBook): LibBook
}