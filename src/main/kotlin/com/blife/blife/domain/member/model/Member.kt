package com.blife.blife.domain.member.model

import com.blife.blife.domain.member.enums.MemberRole
import jakarta.persistence.*


@Entity
class Member(
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    val role: MemberRole,

    @Column(name = "name")
    val name: String,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "password")
    val password: String,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}