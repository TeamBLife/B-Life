package com.blife.blife.infra.external.libapi.dto.lib

import com.blife.blife.infra.external.libapi.dto.Data4libReq
import com.blife.blife.infra.external.libapi.dto.popular.Data4libBookPopularResponseDocs

data class Data4libBookLibResponse (
	val request: Data4libReq,
	val resultNum: Int,
	val numFound: Int,
	val libs: List<Data4libBookLibResponseLibs>
)