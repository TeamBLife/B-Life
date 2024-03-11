package com.blife.blife.domain.review.model

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.infra.postgresql.book.BookEntity
import jakarta.persistence.*

@Entity(name = "bookReview")
class BookReview (
    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: BookEntity,

    @Column(name = "point")
    var point : Float,

    @Column(name = "comment")
    var comment : String,

    @ManyToOne
    @JoinColumn(name = "member_id")
    val member : Member,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
fun BookReview.toResponse(): BookReviewResponse {
    return BookReviewResponse(
        id = id!!,
        name = member.name,
        point = point,
        comment = comment
    )
}
