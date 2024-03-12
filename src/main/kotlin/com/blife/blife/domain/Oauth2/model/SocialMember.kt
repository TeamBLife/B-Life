package com.blife.blife.domain.Oauth2.model

import jakarta.persistence.*

@Entity
class SocialMember(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_member_id")
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    val provider: OAuth2Provider,
    val providerId: String,
    val nickname: String,
    val email: String

){
    companion object{
        fun ofKakao(id: Long, nickname: String, email: String): SocialMember {
            return SocialMember(
                provider = OAuth2Provider.KAKAO,
                providerId = id.toString(),
                nickname = nickname,
                email = email
            )
        }
    }
    
enum class OAuth2Provider{
    KAKAO, NAVER
}

}