package com.blife.blife.infra.store.filestore

import org.springframework.stereotype.Component

@Component
interface IFileStore {

	/**
	 * @return fileName 이 반환 된다. 실패시 null 반환
	 */
	fun saveFile(fileData: Any): String?

	/**
	 * @return 파일 데이터가 반환된다.
	 */
	fun getFile(fileName: String): Any?
}