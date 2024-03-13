package com.blife.blife.infra.external.libapi.dto.lib

import com.blife.blife.infra.external.libapi.dto.Data4libReq

data class Data4libBookLibResponse(
	val request: Data4libReq,
	val resultNum: Int,
	val numFound: Int,
	val libs: List<Data4libBookLibResponseLibs>
)