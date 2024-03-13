package com.blife.blife.domain.checkoutbook.service


import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.domain.book.repository.LibraryRepository
import com.blife.blife.domain.checkoutbook.dto.*
import com.blife.blife.domain.checkoutbook.model.CheckoutBook
import com.blife.blife.domain.checkoutbook.repository.CheckoutRepository
import com.blife.blife.domain.mail.service.MailService
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.member.service.MemberService
import com.blife.blife.domain.review.service.LibBookRepository
import com.blife.blife.domain.wishlist.repository.WishListRepository
import jakarta.mail.internet.MimeMessage
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckoutService(
    private val checkoutRepository: CheckoutRepository,
    private val libraryRepository: LibraryRepository,
    private val libBookRepository: LibBookRepository,
    private val memberRepository: MemberRepository,
    private val wishListRepository: WishListRepository,
    private val javaMailSender: JavaMailSender,
) {

    fun getBookCheckoutStatus(libBookId: Long): LibBookStatusResponse {
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")
        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

       return if (unreturnedBookCount >= libBook.bookQuantity) {
            LibBookStatusResponse(
                libBookName = libBook.book.bookName,
                bookQuantity = libBook.bookQuantity,
                checkoutCount = unreturnedBookCount,
                loanAvailable =  false)
        } else {
             LibBookStatusResponse(
                libBookName = libBook.book.bookName,
                bookQuantity = libBook.bookQuantity,
                checkoutCount = unreturnedBookCount,
                loanAvailable =  true)
        }
    }

    @Transactional
    fun createCheckout(ownerId: Long, request: CheckoutRequest): CheckoutResponse {
        val libBookId = request.libBookId
        val userId = request.memberId
        libraryRepository.findByIdOrNull(ownerId) ?: throw IllegalArgumentException("library")
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")
        val member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member")
        val library = libBook.library
        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

        if (unreturnedBookCount >= libBook.bookQuantity) {
            throw IllegalStateException("이 책의 모든 복사본이 대출 중입니다.")
        }


        // 책을 3권만 빌릴 수 있는 로직이 필요
        val currentLoans = checkoutRepository.countByMemberIdAndReturnedFalse(request.memberId)
        if (currentLoans >= 3) {
            throw IllegalStateException("최대 대여 가능한 책의 수를 초과하였습니다.")
        }


        // 책의 대여일은 빌린 시간으로 부터 14일이다. (시간은 상관없다)
        val checkoutTime = LocalDateTime.now()
        val dueDate = checkoutTime.toLocalDate().plusDays(14)


        val createdCheckout = checkoutRepository.save(
            CheckoutBook(
                libBook = libBook,
                member = member,
                returned = false,
                checkoutTime = checkoutTime,
                returnTime = null,
                dueDate = dueDate
            )
        )
        return CheckoutResponse(
            id = createdCheckout.id!!,
            library = library.libName,
            libBookId = createdCheckout.libBook.id!!,
            memberId = createdCheckout.member.id!!,
            checkoutTime = createdCheckout.checkoutTime!!,
            dueDate = createdCheckout.dueDate
        )
    }


    @Transactional
    fun returnBook(ownerId: Long, request: ReturnBookRequest): ReturnBookResponse {
        val libBookId = request.libBookId
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")
        val library = libBook.library
        val userId = request.memberId
        val checkoutBook = checkoutRepository.findByMemberIdAndLibBookIdAndReturnedFalse(
            userId, libBookId
        ) ?: throw IllegalArgumentException("checkoutBook")

        // 있다면  returned = true 로 변경
        checkoutBook.returned = true
        val returnTime = LocalDateTime.now()
        checkoutRepository.save(checkoutBook)

        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

        if (libBook.bookQuantity > unreturnedBookCount) {
            notifyAvailability(libBook)
        }

        // ReturnBookResponse 로 반환
        return ReturnBookResponse(
            id = checkoutBook.id!!,
            library = library.libName,
            memberId = checkoutBook.member.id!!,
            libBookId = checkoutBook.libBook.id!!,
            checkoutTime = checkoutBook.checkoutTime!!,
            returnTime = returnTime,
            dueDate = checkoutBook.dueDate
        )

    }
        fun notifyAvailability(libBook: LibBook) {
            // 변경된 대출 정보의 책을 찜한 사용자 목록을 가져옵니다.
            val wishLists = wishListRepository.findByLibBookId(libBook.id!!)

            for (wishList in wishLists) {
                sendMail(wishList.member.email, libBook)
            }
        }


        private fun sendMail(email: String, libBook: LibBook) {
            val subject = "도서 대출 알림"
            val text = " 안녕하세요, ${libBook.book.bookName} (가)이 반납이 되어 대출가능합니다. 관심이 있으셨던 책을 대출하세요. "
            val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
            val helper = MimeMessageHelper(mimeMessage, "utf-8")
            helper.setTo(email)
            helper.setSubject(subject)
            helper.setText(text, true) // true to activate multipart

            javaMailSender.send(mimeMessage)
        }
}