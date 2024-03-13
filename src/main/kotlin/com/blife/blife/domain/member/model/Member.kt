package com.blife.blife.domain.member.model

import com.blife.blife.domain.member.enums.MemberRole
import jakarta.persistence.*


@Entity
class Member(
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: MemberRole,

    @Column(name = "name")
    var name: String,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}