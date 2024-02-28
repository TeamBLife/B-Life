package com.blife.blife.domain.library.model

import jakarta.persistence.Entity
import jakarta.persistence.Column
import com.blife.blife.domain.member.model.Member

@Entity
class Library(
    @Column(name = "member_id")
    val member: Member,

    val address: String,

    val libId: Int,

    val libName: String,
) {
    val id: Long? = null
}
