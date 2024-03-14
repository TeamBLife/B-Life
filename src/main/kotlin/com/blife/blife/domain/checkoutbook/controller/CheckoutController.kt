package com.blife.blife.domain.checkoutbook.controller

import com.blife.blife.domain.checkoutbook.dto.*
import com.blife.blife.domain.checkoutbook.service.CheckoutService
import com.blife.blife.domain.review.dto.BookReservationRequest
import com.blife.blife.global.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/checkoutbooks")
class CheckoutController(private val checkoutService: CheckoutService) {

    @Operation(summary = "책 대여가능 조회")
    @GetMapping("/{libBookId}")
    fun getBookCheckoutStatus(
        @PathVariable libBookId: Long,
    ): ResponseEntity<LibBookStatusResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(checkoutService.getBookCheckoutStatus(libBookId))
    }

//    @Operation(summary = " 책 대여 예약")
//    @PostMapping
//    fun createBookReservation(
//        @AuthenticationPrincipal userPrincipal: UserPrincipal,
//        @RequestBody bookReservationRequest: BookReservationRequest,
//    ): ResponseEntity<CheckoutResponse> {
//        val userId = userPrincipal.id
//        return ResponseEntity.status(HttpStatus.CREATED)
//            .body(checkoutService.createReservationBook(userId, bookReservationRequest))
//    }


    @Operation(summary = "책 대여")
    @PreAuthorize("hasRole('OWNER')")
    @PostMapping()
    fun createCheckout(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody checkoutRequest: CheckoutRequest,
    ): ResponseEntity<CheckoutResponse> {
        val ownerId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.CREATED).body(checkoutService.createCheckout(ownerId, checkoutRequest))
    }


    @Operation(summary = "책 반납")
    @PreAuthorize("hasRole('OWNER')")
    @PatchMapping()
    fun returnBook(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody returnBookRequest: ReturnBookRequest,
    ): ResponseEntity<ReturnBookResponse> {
        val ownerId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.OK).body(checkoutService.returnBook(ownerId, returnBookRequest))
    }

}

