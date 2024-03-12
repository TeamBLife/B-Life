package com.blife.blife.event.service

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.mail.service.MailServiceUtils
import com.blife.blife.domain.review.service.LibBookRepository
import com.blife.blife.domain.wishlist.repository.WishListRepository
import jakarta.mail.internet.MimeMessage
import org.springframework.data.repository.findByIdOrNull
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val javaMailSender: JavaMailSender,
    private val wishListRepository: WishListRepository,
) {
    fun notifyAvailability(libBook: LibBook) {
        // 변경된 대출 정보의 책을 찜한 사용자 목록을 가져옵니다.
        val wishLists = wishListRepository.findByLibBookId(libBook.id)

        for (wishList in wishLists) {
            sendMail(wishList.member.email, libBook)
        }
    }


    private fun sendMail(email: String, libBook: LibBook) {
        val subject = "도서 대출 알림"
        val text = " 안녕하세요, ${libBook.book.bookName} 이 반납이 되어 대출가능합니다. 관심이 있으셨던 책을 대출하세요. "
        val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(mimeMessage, "utf-8")
        helper.setTo(email)
        helper.setSubject(subject)
        helper.setText(text, true) // true to activate multipart

        javaMailSender.send(mimeMessage)
    }
}
