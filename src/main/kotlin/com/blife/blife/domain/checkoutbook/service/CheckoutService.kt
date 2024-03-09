package com.blife.blife.domain.checkoutbook.service

import com.blife.blife.domain.book.model.toResponse
import com.blife.blife.domain.book.repository.LibraryRepository
import com.blife.blife.domain.checkoutbook.dto.*
import com.blife.blife.domain.checkoutbook.model.CheckoutBook
import com.blife.blife.domain.checkoutbook.repository.CheckoutRepository
import com.blife.blife.domain.member.repository.MemberRepository
import com.blife.blife.domain.review.service.LibBookRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CheckoutService(
    private val checkoutRepository: CheckoutRepository,
    private val libraryRepository: LibraryRepository,
    private val libBookRepository: LibBookRepository,
    private val memberRepository: MemberRepository,
) {

    fun getBookCheckoutStatus(libBookId : Long) : LibBookStatusResponse{
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")

        return  libBook.toResponse()
    }
    @Transactional
    fun createCheckout(ownerId: Long, request: CheckoutRequest): CheckoutResponse {
        val libBookId = request.libBookId
        val userId = request.memberId
        // request.LibBook 이 현재 대여가능한 상태인지 확인
        libraryRepository.findByIdOrNull(ownerId) ?: throw IllegalArgumentException("library")
        val libBook = libBookRepository.findByIdOrNull(libBookId) ?: throw IllegalArgumentException("libBook")

        if (libBook.checkoutCount >= libBook.copyCount) {
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

        //책을 빌리게 된다면 빌린 책의 권수가 늘어난다.

        libBook.apply {
            checkoutCount++
            loanAvailable = (checkoutCount < copyCount) // 대출 가능 여부 업데이트
        }
        libBookRepository.save(libBook)


        // 새로운 CheckoutBook 생성 및 저장
        val newCheckout = CheckoutBook(
            libBook = libBook,
            member = memberRepository.findByIdOrNull(userId) ?: throw IllegalArgumentException("member"),
            returned = false,
            checkoutTime = checkoutTime,
            returnTime = null,
            dueDate = dueDate
        )
        checkoutRepository.save(newCheckout)

        // CheckoutResponse 반환
        return CheckoutResponse(
            id = newCheckout.id!!,
            libBookId = newCheckout.libBook.id!!,
            memberId = newCheckout.member.id!!,
            checkoutTime = newCheckout.checkoutTime!!,
            dueDate = newCheckout.dueDate
        )
    }

    @Transactional
    fun returnBook(ownerId: Long, request: ReturnBookRequest): ReturnBookResponse {
        val libBookId = request.libBookId
        val userId = request.memberId
        val checkoutBook = checkoutRepository.findByMemberIdAndLibBookIdAndReturnedFalse(
            userId, libBookId
        ) ?: throw IllegalArgumentException("checkoutBook")

        // 있다면  returned = true 로 변경
        checkoutBook.returned = true
        val returnTime = LocalDateTime.now()
        checkoutRepository.save(checkoutBook)


        // 해당 책을 반납하면 checkoutCount 의 숫자가 하나 준다.

        val libBook = checkoutBook.libBook.apply {
            checkoutCount = (checkoutCount - 1).toShort()
            loanAvailable = true
            // libBook 엔티티의 loanAvailable가 true로 변환됨
            loanAvailable = copyCount > checkoutCount
        }
        libBookRepository.save(libBook)

        // ReturnBookResponse 로 반환
        return ReturnBookResponse(
            id = checkoutBook.id!!,
            memberId = checkoutBook.member.id!!,
            libBookId = checkoutBook.libBook.id!!,
            checkoutTime = checkoutBook.checkoutTime!!,
            returnTime = returnTime,
            dueDate = checkoutBook.dueDate
        )
    }
}