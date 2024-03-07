package com.blife.blife.infra.external.libapi.dto.popular

data class Data4libBookPopularResponseDoc(
	val no: Int,
	val ranking: String,
	val bookname: String,
	val authors: String,
	val publisher: String,
	val publication_year: String,
	val isbn13: String,
	val addition_symbol: String,
	val vol: String,
	val class_no: String,
	val class_nm: String,
	val loan_count: String,
	val bookImageURL: String,
	val bookDtlUrl: String
)