package com.blife.blife.domain.wishlist.controller

import com.blife.blife.domain.wishlist.service.WishService
import com.blife.blife.global.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/libBook")
class WishController(private val wishService: WishService) {

    @PostMapping("/wish/{libBookId}")
    fun createOrRemoveWish(
        @PathVariable libBookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Any> {
        val userId = userPrincipal.id
            wishService.createWishList(userId, libBookId)
            return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}