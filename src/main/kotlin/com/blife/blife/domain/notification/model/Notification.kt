package com.blife.blife.domain.notification.model

import jakarta.persistence.*
import java.util.*

class Notification (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userId: Long, // 수신자 ID
    val message: String, // 알림 메시지
    val type: String, // 알림 타입
    val read: Boolean = false, // 읽음 상태
    @Temporal(TemporalType.TIMESTAMP)
    val createdAt: Date = Date()
)