package com.blife.blife.domain.checkoutbook.controller

import com.blife.blife.domain.checkoutbook.dto.*
import com.blife.blife.domain.checkoutbook.service.CheckoutService
import com.blife.blife.global.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/checkoutbooks")
class CheckoutController(private val checkoutService: CheckoutService) {

    @Operation(summary = "책 대여가능 조회")
    @GetMapping("/{libBookId}")
    fun getBookCheckoutStatus(
        @PathVariable libBookId: Long,
    ):
            ResponseEntity<LibBookStatusResponse> {
        checkoutService.getBookCheckoutStatus(libBookId)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

//    @Operation(summary = "책 대여 예약")
//    @PreAuthorize("hasRole('MEMBER')")
//    @PostMapping()
//    fun reservationBook(
//        @AuthenticationPrincipal userPrincipal: UserPrincipal,
//        @RequestBody reservationRequest: ReservationRequest,
//    ): ResponseEntity<List<CheckoutResponse>> {
//        val userId = userPrincipal.id
//        checkoutService.reservationBook(userId, reservationRequest)
//        return ResponseEntity.status(HttpStatus.CREATED).build()
//    }

    @Operation(summary = "책 대여")
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping()
    fun createCheckout(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody checkoutRequest: CheckoutRequest,
    ): ResponseEntity<CheckoutResponse> {
        val ownerId = userPrincipal.id
        checkoutService.createCheckout(ownerId, checkoutRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "책 반납")
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping()
    fun returnBook(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody returnBookRequest: ReturnBookRequest,
    ): ResponseEntity<ReturnBookResponse> {
        val ownerId = userPrincipal.id
        checkoutService.returnBook(ownerId, returnBookRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

}

