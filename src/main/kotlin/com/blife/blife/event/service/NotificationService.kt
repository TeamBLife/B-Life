package com.blife.blife.event.service

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.review.service.LibBookRepository
import com.blife.blife.domain.wishlist.repository.WishListRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NotificationService (
    private val wishListRepository: WishListRepository,
    private val libBookRepository: LibBookRepository
) {
    fun notifyAvailability(libBook: LibBook) {
        val notificationCount = libBook.
        // 대출 가능한 책을 찜한 사용자 목록을 가져옵니다.
        val wishLists = wishListRepository.findByLibBookIdAndNotificationCountLessThan(libBook.id!!, notificationCount)
        val libBook = libBookRepository.findByIdOrNull(Lib)
        // 알림을 보냅니다.
        for (wishList in wishLists) {
            // 알림 메시지를 보내는 로직
            // 예: sendNotification(wishList.member, "The book ${libBook.title} is now available for loan.")
            // notificationCount를 업데이트합니다.
            wishList.increaseNotificationCount()
        }
        // 업데이트된 WishList를 저장합니다.
        wishListRepository.saveAll(wishLists)
    }
}