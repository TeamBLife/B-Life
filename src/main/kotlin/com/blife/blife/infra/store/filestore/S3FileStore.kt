package com.blife.blife.infra.store.filestore

class S3FileStore: IFileStore {
	/**
	 * @return fileName 이 반환 된다. 실패시 null 반환
	 */
	override fun saveFile(fileData: Any): String? {
		TODO("Not yet implemented")
	}

	/**
	 * @return 파일 데이터가 반환된다.
	 */
	override fun getFile(fileName: String): Any? {
		TODO("Not yet implemented")
	}
}