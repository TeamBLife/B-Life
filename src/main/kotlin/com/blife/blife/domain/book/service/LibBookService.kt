package com.blife.blife.domain.book.service

import com.blife.blife.domain.review.service.LibBookRepository
import com.blife.blife.event.LoanAvailableEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class LibBookService(
    private val libBookRepository: LibBookRepository,
    private val eventPublisher: ApplicationEventPublisher
) {
    fun updateLoanAvailability(libBookId: Long, available: Boolean) {
        val libBook = libBookRepository.findById(libBookId).orElseThrow {
            NoSuchElementException("도서가 존재하지 않습니다.")
        }

        val wasAvailable = libBook.loanAvailable
        libBook.updateLoanAvailable(available)

        // 상태 변경 후 저장
        libBookRepository.save(libBook)

        // 이전에는 대출 불가능 상태였고, 지금은 대출 가능 상태로 바뀌었다면 이벤트 발행
        if (!wasAvailable && available) {
            eventPublisher.publishEvent(LoanAvailableEvent(libBook))
        }
    }
}