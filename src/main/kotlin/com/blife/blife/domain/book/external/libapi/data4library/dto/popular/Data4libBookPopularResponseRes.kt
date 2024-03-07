package com.blife.blife.domain.book.external.libapi.data4library.dto.popular


data class Data4libReq(
	val pageNo: Int,
	val pageSize: Int,
)

data class Data4libBookPopularResponseRes(
	val request: Data4libReq,
	val resultNum: Int,
	val numFound: Int,
	val docs: List<Data4libBookPopularResponseDocs>
)