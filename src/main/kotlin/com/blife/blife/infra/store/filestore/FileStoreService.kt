package com.blife.blife.infra.store.filestore

import org.springframework.stereotype.Component

@Component
class FileStoreService(
	private val fileStore: IFileStore
) {
	private fun checkFileFormThen(func: () -> Unit) {
		if (true) {
			func()
		}

		throw TODO("파일 양식에 맞지 않음")
	}

	/**
	 * NOTE:
	 *  1. CSV파일로 받아온다.
	 *  2. 양식이 맞는지 확인한다.
	 *  3. 양식이 맞다면 CSV파일을 미리 저장시켜 놓는다.
	 */
	fun saveFile(fileData: Any) {

	}
}