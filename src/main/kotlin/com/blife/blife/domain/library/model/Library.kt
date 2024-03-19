package com.blife.blife.domain.library.model

import com.blife.blife.domain.library.enums.REGION

class Library(
	val memberId: Long,
	val libName: String,
	val address: String,
	val libId: Long,
	var closed: String,
	var operatingTime: String,
	var tel: String,
	var homepage: String,
	val latitude: Float,
	val longitude: Float,
	val region: REGION,
)
