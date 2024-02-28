package com.blife.blife.domain.review.model

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*

@Entity
(name = "review")
class Review (
    @ManyToOne
    @Column(name = "book_id")
    val book : Book,

    val status : String,

    val point : Float,

    var comment : String,

    @ManyToOne
    @Column(name = "member_id")
    val member : Member,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}