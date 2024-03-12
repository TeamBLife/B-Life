package com.blife.blife.domain.wishlist.model

import com.blife.blife.domain.book.model.Book
import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.member.model.Member
import jakarta.persistence.*
@Entity(name = "wishList")
class WishList (
    @ManyToOne
    @JoinColumn(name = "lib_book_id")
    var libBook: LibBook,

    @ManyToOne
    @JoinColumn(name = "member_id")
    var member : Member,

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}