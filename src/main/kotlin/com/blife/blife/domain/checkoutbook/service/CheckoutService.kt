package com.blife.blife.domain.checkoutbook.service


import com.blife.blife.domain.checkoutbook.dto.*
import com.blife.blife.domain.checkoutbook.model.CheckoutBook
import com.blife.blife.domain.checkoutbook.repository.CheckoutRepository
import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.global.util.mail.service.MailService
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.dto.BookReservationRequest
import com.blife.blife.domain.wishlist.repository.WishListRepository
import com.blife.blife.infra.postgresql.library.JpaLibBookRepository
import com.blife.blife.infra.postgresql.library.JpaLibraryRepository
import com.blife.blife.infra.postgresql.library.entity.LibBookEntity
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckoutService(
    private val checkoutRepository: CheckoutRepository,
    private val libraryRepository: JpaLibraryRepository,
    private val jpaLibBookRepository: JpaLibBookRepository,
    private val memberRepository: MemberRepository,
    private val wishListRepository: WishListRepository,
    private val mailService: MailService,
) {

    fun getBookCheckoutStatus(libBookId: Long): LibBookStatusResponse {
        val libBook = jpaLibBookRepository.findByIdOrNull(libBookId) ?: throw TODO("도서관에서 책을 찾을 수 없습니다.")
        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

        return if (unreturnedBookCount >= libBook.totalBookCount) {
            LibBookStatusResponse(
                libBookName = libBook.book.bookName,
                bookQuantity = libBook.totalBookCount,
                checkoutCount = unreturnedBookCount,
                loanAvailable = false
            )
        } else {
            LibBookStatusResponse(
                libBookName = libBook.book.bookName,
                bookQuantity = libBook.totalBookCount,
                checkoutCount = unreturnedBookCount,
                loanAvailable = true
            )
        }
    }

    fun createReservationBook(userId: Long, request: BookReservationRequest): CheckoutResponse {
        val libBookId = request.libBookId
        val libBook = jpaLibBookRepository.findByIdOrNull(libBookId) ?: throw TODO("도서관에서 책을 찾을 수 없습니다.")
        val library = libBook.lib
        val member = memberRepository.findByIdOrNull(userId) ?: throw TODO("멤버를 찾을 수 없습니다.")
        val possibleBookQuantity = 3
        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

        if (unreturnedBookCount >= libBook.totalBookCount) {
            throw IllegalStateException("이 책의 모든 책이 대출 중입니다.")
        }


        // 책을 3권만 빌릴 수 있는 로직이 필요
        val currentLoans = checkoutRepository.countByMemberIdAndReturnedFalse(userId)
        if (currentLoans >= possibleBookQuantity) {
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
    fun createCheckout(
        ownerId: Long,
        hasRole:
        Collection<GrantedAuthority>,
        request: CheckoutRequest,
    ): CheckoutResponse {
        val libBookId = request.libBookId
        val userId = request.memberId
        val libBook = jpaLibBookRepository.findByIdOrNull(libBookId) ?: throw TODO("도서관에서 책을 찾을 수 없습니다.")
        val member = memberRepository.findByIdOrNull(userId) ?: throw TODO("유저를 찾을 수 없습니다.")
        val library = libBook.lib
        val possibleBookQuantity = 3
        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)
        val isOwner = hasRole.any { grantedAuthority ->
            grantedAuthority.getAuthority() == "ROLE_${MemberRole.OWNER.name}"
        }
        if (!isOwner) {
            throw TODO("권한이 없습니다. ")
        }
        if (unreturnedBookCount >= libBook.totalBookCount) {
            throw TODO("이 책의 모든 책이 대출 중입니다.")
        }


        // 책을 3권만 빌릴 수 있는 로직이 필요
        val currentLoans = checkoutRepository.countByMemberIdAndReturnedFalse(request.memberId)
        if (currentLoans >= possibleBookQuantity) {
            throw TODO("최대 대여 가능한 책의 수를 초과하였습니다.")
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
    fun returnBook(
        ownerId: Long,
        hasRole: Collection<GrantedAuthority>,
        request: ReturnBookRequest,
    ): ReturnBookResponse {
        val libBookId = request.libBookId
        val libBook = jpaLibBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")
        val library = libBook.lib
        val userId = request.memberId
        val isOwner = hasRole.any { grantedAuthority ->
            grantedAuthority.getAuthority() == "ROLE_${MemberRole.OWNER.name}"
        }
        if (!isOwner) {
            throw TODO("권한이 없습니다. ")
        }
        val checkoutBook = checkoutRepository.findByMemberIdAndLibBookIdAndReturnedFalse(
            userId, libBookId
        ) ?: throw TODO("checkoutBook")
        // 있다면  returned = true 로 변경
        checkoutBook.returned = true
        val returnTime = LocalDateTime.now()
        checkoutRepository.save(checkoutBook)

        val unreturnedBookCount = checkoutRepository.countByLibBookIdAndReturnedFalse(libBookId)

        if (libBook.totalBookCount > unreturnedBookCount) {
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

    fun notifyAvailability(libBook: LibBookEntity) {
        // 변경된 대출 정보의 책을 찜한 사용자 목록을 가져옵니다.
        val wishLists = wishListRepository.findByLibBookId(libBook.id!!)

        for (wishList in wishLists) {
            val subject = "도서 대출 알림"
            val text = " 안녕하세요, ${libBook.book.bookName} (가)이 반납이 되어 대출가능합니다. 관심이 있으셨던 책을 대출하세요. "
            mailService.sendMail(wishList.member.email, subject, text, true)
        }
    }
}