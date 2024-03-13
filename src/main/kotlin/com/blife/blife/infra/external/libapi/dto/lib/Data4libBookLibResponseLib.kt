package com.blife.blife.infra.external.libapi.dto.lib

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.library.model.Library

data class Data4libBookLibResponseLib(
	val libCode: String,
	val libName: String,
	val address: String,
	val tel: String,
	val fax: String,
	val latitude: String,
	val longitude: String,
	val homepage: String,
	val closed: String,
	val operatingTime: String,
	val BookCount: String,
) {
	fun convertToLibrary(memberId: Long) = Library(
		libName = libName,
		libId = libCode.toLong(),
		operatingTime = operatingTime,
		tel = tel,
		closed = closed,
		longitude = longitude.toFloat(),
		homepage = homepage,
		address = address,
		memberId = memberId,
		region = getRegion(),
		latitude = latitude.toFloat()
	)

	private fun getRegion(): REGION {
		return 1 as REGION
	}
}