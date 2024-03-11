package com.blife.blife.domain.notification.repository

import com.blife.blife.domain.notification.model.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByUserIdAndRead(userId: Long, read: Boolean): List<Notification>
}