package com.blife.blife.domain.wishlist.repository

import com.blife.blife.domain.wishlist.model.WishList
import org.springframework.data.jpa.repository.JpaRepository

interface WishListRepository : JpaRepository<WishList, Long> {

    fun existsByMemberIdAndLibBookId(memberId: Long, libBookId: Long): Boolean

    fun findByMemberIdAndLibBookId(memberId: Long, libBookId: Long) : WishList

    fun findByLibBookId(libBookId: Long) : List<WishList>
}