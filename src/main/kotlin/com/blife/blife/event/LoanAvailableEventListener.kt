package com.blife.blife.event

import com.blife.blife.domain.book.model.LibBook
import com.blife.blife.event.service.NotificationService
import jakarta.persistence.PostPersist
import jakarta.persistence.PostUpdate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class LoanAvailableEventListener {

    @Autowired
    lateinit var eventPublisher: ApplicationEventPublisher
    @PostPersist
    @PostUpdate
    fun onLoanAvailableChange(libBook: LibBook) {
        // 메서드 구현...
    }
}