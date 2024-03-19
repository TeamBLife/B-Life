package com.blife.blife.infra.external.libapi.dto.lib

import com.blife.blife.domain.library.enums.REGION
import com.blife.blife.domain.library.model.Library

// TODO : 변수명은 Snake Case로 작업 하고 JsonProperty를 이용해서 데이터를 받아 올 수 있게 리펙토링

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
		val split = address.split(" ")
		val city = split[0]
		val district = split[1]
		return REGION.getCodeByCityAndDistrict(city, district)
	}
}