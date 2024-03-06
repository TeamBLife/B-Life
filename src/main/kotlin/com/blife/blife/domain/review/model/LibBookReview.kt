package com.blife.blife.domain.review.model

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.member.model.Member
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import jakarta.persistence.*

@Entity
(name = "libBookReview")
class LibBookReview (
    @ManyToOne
    @Column(name = "libBook_id")
    var libBook: LibBook,

    @Column(name = "point")
    var point : Float,

    @Column(name = "comment")
    var comment : String,

    @ManyToOne
    @Column(name = "member_id")
    val member : Member,

    @Column(name = "status")
    var status : String,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


fun LibBookReview.toLibBookResponse(): LibBookReviewResponse{
    return LibBookReviewResponse(
        name = member.name,
        point = point,
        comment = comment,
        status = status,
    )
}