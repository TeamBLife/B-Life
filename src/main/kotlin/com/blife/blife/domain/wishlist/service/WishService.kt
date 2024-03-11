package com.blife.blife.domain.wishlist.service

import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.WishListResponse
import com.blife.blife.domain.review.service.LibBookRepository
import com.blife.blife.domain.wishlist.model.WishList
import com.blife.blife.domain.wishlist.repository.WishListRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class WishService (
    private val wishListRepository: WishListRepository,
    private val memberRepository: MemberRepository,
    private val libBookRepository: LibBookRepository
){
    fun createWishList(userId: Long, libBookIdId: Long) : Any {
        val libBook = libBookRepository.findByIdOrNull(libBookIdId) ?: throw IllegalArgumentException("book in library")
        val member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member")
        // 같은 사용자가 같은 책을 여러번 찜하였는지 확인, 찜 한 경우가 없을때만 생산 로직 실행, 있을 경우에는 삭제 로직(deleteWishList()
        if (wishListRepository.existsByMemberIdAndLibBookId(userId,libBookIdId)){
            return deleteWishList(userId, libBookIdId)
        } else {
        val wishList = WishList(
            member = member, libBook =  libBook,  notificationCount = 0, maxNotificationCount = 3)
            wishListRepository.save(wishList)
            return WishListResponse(wishList.id!!, member.id!!, libBook.id!!)
        }
    }

    fun deleteWishList(userId: Long, libBookIdId: Long)  {
        val wishList = wishListRepository.findByMemberIdAndLibBookId(userId , libBookIdId)
        return wishListRepository.delete(wishList)
    }
}