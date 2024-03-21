package com.blife.blife.domain.member.model

import com.blife.blife.domain.member.enums.MemberRole
import com.blife.blife.domain.member.enums.MemberStatus
import jakarta.persistence.*


@Entity
class Member(
    // 일반 유저인지 소셜로그인 유저인지
    @Column(name = "is_social_user")
    val isSocialUser : Boolean,

    @Column(name = "provider")
    val provider : OAuth2Provider? = null,

    val providerId : String? = null,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    var role: MemberRole,

    @Column(name = "name")
    var nickname: String,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "password")
    val password: String? = null,

    @Column(name = "is_deleted")
    var isDeleted: Boolean = false,

    ) {
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    var status: MemberStatus = MemberStatus.WAIT

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    enum class OAuth2Provider {
        KAKAO, NAVER
    }


}