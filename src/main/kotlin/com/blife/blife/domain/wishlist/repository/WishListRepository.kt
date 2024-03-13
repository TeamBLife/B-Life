package com.blife.blife.domain.wishlist.repository

import com.blife.blife.domain.wishlist.model.WishList
import org.springframework.data.jpa.repository.JpaRepository

interface WishListRepository : JpaRepository<WishList, Long> {

    fun existsByMember_IdAndLibBook_Id(memberId: Long, libBookId: Long): Boolean

    fun findByMemberIdAndLibBookId(memberId: Long, libBookId: Long) : WishList
    fun findByLibBook_IdAndNotificationCountLessThan(libBookId : Long, maxNotificationCount : Short) :List <WishList>


}