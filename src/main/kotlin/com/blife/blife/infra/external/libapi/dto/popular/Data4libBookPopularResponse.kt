package com.blife.blife.infra.external.libapi.dto.popular

import com.blife.blife.infra.external.libapi.dto.Data4libReq


data class Data4libBookPopularResponse(
	val request: Data4libReq,
	val resultNum: Int,
	val numFound: Int,
	val docs: List<Data4libBookPopularResponseDocs>
)
