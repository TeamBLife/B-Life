package com.blife.blife.domain.wishlist.service

import com.blife.blife.domain.book.repository.BookRepository
import com.blife.blife.domain.book.repository.LibBookRepository
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.WishListResponse
import com.blife.blife.domain.review.repository.BookReviewRepository
import com.blife.blife.domain.wishlist.repository.WishListRepository
import jakarta.persistence.Id
import org.springframework.data.repository.findByIdOrNull

class WishService (
    private val wishListRepository: WishListRepository,
    private val memberRepository: MemberRepository,
    private val libBookRepository: LibBookRepository
){
    fun createWishList(userId: Long, libBookIdId: Long) : WishListResponse {
        val book = libBookRepository.findByIdOrNull(libBookIdId) ?: throw IllegalArgumentException("book in library")
        val member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member")
    }
}