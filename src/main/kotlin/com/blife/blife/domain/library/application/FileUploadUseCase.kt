package com.blife.blife.domain.library.application

import com.blife.blife.infra.store.filestore.FileStoreService

class FileUploadUseCase(
	private val fileStoreService: FileStoreService
) {
	fun addBookFile(fileData: Any) {
		fileStoreService.saveFile(fileData)
	}
}