package com.blife.blife.domain.member.model

import jakarta.persistence.*


@Entity
class Member(

    @Column(name = "name")
    val name: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}