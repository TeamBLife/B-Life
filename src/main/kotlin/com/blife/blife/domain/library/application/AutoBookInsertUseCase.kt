package com.blife.blife.domain.library.application

import com.blife.blife.infra.store.filestore.FileStoreService

class AutoBookInsertUseCase(
	private val fileStoreService: FileStoreService
) {
	/**
	 * NOTE:
	 *  1. DB에 LibId 기반으로 대기열이 걸려 있는 값들 유무 확인
	 *  - DB에 대기열이 있을 경우
	 *  2. DB에서 일정 갯수만큼만 가지고 와서 비동기로 처리
	 *  - DB에 대기열이 없을 경우
	 *  2. CSV 파일이 있는지 확인한다.
	 *  3. 하나의 CSV 파일을 가지고 오고 일정 갯수만큼 비동기로 처리시키고 await 을 걸어둔다.
	 */
	fun bookInsertFromFile() {}


}