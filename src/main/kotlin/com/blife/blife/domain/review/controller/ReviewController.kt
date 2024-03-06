package com.blife.blife.domain.review.controller

import com.blife.blife.domain.review.dto.BookReviewRequest
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/review")
class ReviewController(
    private val reviewService: ReviewService,
) {
    @Operation(summary = "책 리뷰 조회")
    @GetMapping("/book/{bookId}")
    fun getBookReviewList(
        @PathVariable bookId: Long,
        @PageableDefault pageable: Pageable,
    ): ResponseEntity<Page<BookReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewListByBookId(bookId, pageable))
    }

    @Operation(summary = "책 단일리뷰 조회")
    @GetMapping("/book/{bookReviewId}")
    fun getBookReview(
        @PathVariable bookReviewId: Long,
    ): ResponseEntity<BookReviewResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewByBookId(bookReviewId))
    }


    @Operation(summary = "도서관내 책 리뷰 조회")
    @GetMapping("/libBook/{libBookId}")
    fun getBookReviewListInLib(
        @PathVariable libBookId: Long,
        @PageableDefault pageable: Pageable,
    ): ResponseEntity<Page<LibBookReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewListByLibBookId(libBookId, pageable))
    }

    @Operation(summary = "책 단일리뷰 조회")
    @GetMapping("/libBook/{libBookReviewId}")
    fun getBookReviewInLib(
        @PathVariable libBookReviewId: Long,
    ): ResponseEntity<LibBookReviewResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getBookReviewInLib(libBookReviewId))
    }

    @Operation(summary = "책 리뷰 생성")
    @PostMapping("/book/{bookId}")
    fun createBookReview(
        @PathVariable bookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        bookReviewRequest: BookReviewRequest,
    ): ResponseEntity<BookReviewResponse> {
        val userId = userPrincipal.id
        reviewService.createBookReview(bookId, userId, bookReviewRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "도서관내 책 리뷰 생성")
    @PostMapping("/libBook/{libBookId}")
    fun createBookReviewInLib(
        @PathVariable libBookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        libBookReviewRequest: LibBookReviewRequest,
    ): ResponseEntity<LibBookReviewResponse> {
        val userId = userPrincipal.id
        reviewService.createBookReviewInLib(libBookId, userId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "책 리뷰 수정")
    @PatchMapping("/book/{bookReviewId}")
    fun updateBookReview(
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        bookReviewRequest: BookReviewRequest,
    ): ResponseEntity<BookReviewResponse> {
        val userId = userPrincipal.id
        reviewService.updateBookReview(bookReviewId, userId, bookReviewRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(summary = "도서관내 책 리뷰 수정")
    @PatchMapping("/libBook/{libBookReviewId}")
    fun updateBookReviewInLib(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        libBookReviewRequest: LibBookReviewRequest,
    ): ResponseEntity<LibBookReviewResponse> {
        val userId = userPrincipal.id
        reviewService.updateBookReviewInLib(libBookReviewId, userId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(summary = "책 리뷰 삭제")
    @DeleteMapping("/book/{bookReviewId}")
    fun deleteReview(
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        reviewService.deleteReview(bookReviewId, userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @Operation(summary = "도서관 내 책 리뷰 삭제")
    @DeleteMapping("/libBook/{libBookReviewId}")
    fun deleteLibBookReview(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        reviewService.deleteLibBookReview(libBookReviewId, userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}