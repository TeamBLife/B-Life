package com.blife.blife.event

import com.blife.blife.event.service.NotificationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component

class LoanAvailableEventListener(
    private val notificationService: NotificationService
) {

    @EventListener
    fun onLoanAvailableEvent(event: LoanAvailableEvent) {
        val libBook = event.libBook
        // 여기서 알림 로직을 실행합니다.
        notificationService.notifyAvailability(libBook)
    }
}