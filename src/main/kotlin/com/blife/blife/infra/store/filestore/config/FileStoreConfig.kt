package com.blife.blife.infra.store.filestore.config

import com.blife.blife.infra.store.filestore.FileStoreService
import com.blife.blife.infra.store.filestore.LocalFileStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FileStoreConfig {
	@Bean
	fun fileStoreService() = FileStoreService(LocalFileStore())
}