package com.blife.blife.domain.library.controller.dto

import com.blife.blife.domain.library.model.Library

data class LibraryResponse(
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
	val regionCode: Long,
) {
	companion object {
		fun from(library: Library) = LibraryResponse(
			memberId = library.memberId,
			regionCode = library.region.code,
			latitude = library.latitude,
			homepage = library.homepage,
			address = library.address,
			tel = library.tel,
			operatingTime = library.operatingTime,
			longitude = library.longitude,
			libId = library.libId,
			closed = library.closed,
			libName = library.libName
		)
	}
}
