package com.blife.blife.infra.external.booksearchapi.dto.kakao

import com.blife.blife.domain.book.model.Book
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KakaoBookSearchResponseDocument(
	val title: String,
	val contents: String,
	val url: String,
	val isbn: String,
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") val datetime: LocalDateTime,
	val authors: List<String>,
	val publisher: String,
	val translators: List<String>,
	val salePrice: Int,
	val price: Int,
	val thumbnail: String,
	val status: String
) {

	fun convertToBook() = Book(
		title = title,
		description = contents,
		author = authors.joinToString(","),
		coverUrl = thumbnail,
		isbn10 = getIsbn10(),
		isbn13 = getIsbn13(),
		publicationDate = datetime
	)

	private fun getIsbn10(): Long {
		return this.isbn.split(" ")[0]
			.filter { it.isDigit() }
			.toLong()
	}

	private fun getIsbn13(): Long {
		return this.isbn.split(" ")
			.takeIf { it.size == 2 }
			?.let { it[1].toLong() }
			?: throw TODO("")
	}
}