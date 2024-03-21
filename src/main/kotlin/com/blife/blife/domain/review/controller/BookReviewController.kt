package com.blife.blife.domain.review.controller

import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.BookReviewRequest
import com.blife.blife.domain.review.dto.BookReviewResponse
import com.blife.blife.domain.review.service.BookReviewService
import com.blife.blife.global.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bookreviews")
class BookReviewController(
    private val bookReviewService: BookReviewService,
) {
    @Operation(summary = "책 리뷰 조회")
    @GetMapping("/books/{bookId}")
    fun getBookReviewList(
        @PathVariable bookId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
    ): ResponseEntity<Page<BookReviewResponse>> {
        val pageable = PageRequest.of(page, size)
        val bookReviews = bookReviewService.getReviewListByBookId(bookId, pageable)
        val bookReviewResponses = bookReviews.map { BookReviewResponse.from(it) }
        return ResponseEntity.ok(bookReviewResponses)
    }

    @Operation(summary = "책 평점")
    @GetMapping("/books/{bookId}/point")
    fun getAverage(
        @PathVariable bookId: Long,
    ): ResponseEntity<AverageScoreDto> {
        return ResponseEntity.status(HttpStatus.OK).body(bookReviewService.getAverage(bookId))
    }

    @Operation(summary = "책 리뷰 생성")
    @PostMapping("/books/{bookId}")
    fun createBookReview(
        @PathVariable bookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody bookReviewRequest: BookReviewRequest,
    ): ResponseEntity<BookReviewResponse> {
        val userId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(bookReviewService.createBookReview(bookId, userId, bookReviewRequest))
    }


    @Operation(summary = "책 리뷰 수정")
    @PatchMapping("/{bookReviewId}")
    fun updateBookReview(
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody bookReviewRequest: BookReviewRequest,
    ): ResponseEntity<BookReviewResponse> {
        val userId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.OK)
            .body(bookReviewService.updateBookReview(bookReviewId, userId, bookReviewRequest))
    }


    @Operation(summary = "책 리뷰 삭제")
    @DeleteMapping("/{bookReviewId}")
    fun deleteReview(
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        bookReviewService.deleteReview(bookReviewId, userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

}