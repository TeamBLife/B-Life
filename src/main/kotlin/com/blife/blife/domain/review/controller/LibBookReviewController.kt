package com.blife.blife.domain.review.controller

import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.service.LibBookReviewService
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
@RequestMapping("/libbookreviews")
class LibBookReviewController(
    private val libBookReviewService: LibBookReviewService,
) {

    @Operation(summary = "도서관내 책 리뷰 조회")
    @GetMapping("/libbooks/{libBookId}")
    fun getBookReviewListInLib(
        @PathVariable libBookId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "5") size: Int,
    ): ResponseEntity<Page<LibBookReviewResponse>> {
        val pageable = PageRequest.of(page, size)
        val libBookReviews = libBookReviewService.getReviewListByLibBookId(libBookId, pageable)
        val libBookReviewResponse = libBookReviews.map { LibBookReviewResponse.from(it) }
        return ResponseEntity.ok(libBookReviewResponse)
    }

    @Operation(summary = "책 평점")
    @GetMapping("/libbooks/{libBookReviewId}/point")
    fun getAverage(
        @PathVariable libBookReviewId: Long,
    ): ResponseEntity<AverageScoreDto> {
        return ResponseEntity.status(HttpStatus.OK).body(libBookReviewService.getAverage(libBookReviewId))
    }


    @Operation(summary = "도서관내 책 리뷰 생성")
    @PostMapping("/libbooks/{libBookId}")
    fun createBookReviewInLib(
        @PathVariable libBookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody libBookReviewRequest: LibBookReviewRequest,
    ): ResponseEntity<LibBookReviewResponse> {
        val userId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(libBookReviewService.createBookReviewInLib(libBookId, userId, libBookReviewRequest))
    }


    @Operation(summary = "도서관내 책 리뷰 수정")
    @PatchMapping("/{libBookReviewId}")
    fun updateBookReviewInLib(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody libBookReviewRequest: LibBookReviewRequest,
    ): ResponseEntity<LibBookReviewResponse> {
        val userId = userPrincipal.id
        return ResponseEntity.status(HttpStatus.OK)
            .body(libBookReviewService.updateBookReviewInLib(libBookReviewId, userId, libBookReviewRequest))
    }


    @Operation(summary = "도서관 내 책 리뷰 삭제")
    @DeleteMapping("/{libBookReviewId}")
    fun deleteLibBookReview(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit> {
        val userId = userPrincipal.id
        libBookReviewService.deleteLibBookReview(libBookReviewId, userId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}