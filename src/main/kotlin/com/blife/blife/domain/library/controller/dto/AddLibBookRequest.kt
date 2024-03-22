package com.blife.blife.domain.library.controller.dto

data class AddLibBookRequest(
	val isbn13: Long,
	val totalBookCount: Long
)