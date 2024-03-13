package com.blife.blife.domain.library.model

import com.blife.blife.domain.library.enums.REGION
import java.time.LocalDateTime

class Library(
	val memberId: Long,
	val libName: String,
	val address: String,
	val libId: Long,
	val closed: String,
	val operatingTime: String,
	val tel: String,
	val homepage: String,
	val latitude: Float,
	val longitude: Float,
	val region: REGION,
)
