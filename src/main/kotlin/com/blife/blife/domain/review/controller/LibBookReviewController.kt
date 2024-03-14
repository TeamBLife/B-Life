package com.blife.blife.domain.review.controller

import com.blife.blife.domain.review.dto.AverageScoreDto
import com.blife.blife.domain.review.dto.LibBookReviewRequest
import com.blife.blife.domain.review.dto.LibBookReviewResponse
import com.blife.blife.domain.review.service.LibBookReviewService
import com.blife.blife.global.security.UserPrincipal
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
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/libbookreviews")
class LibBookReviewController(
    private val libBookReviewService: LibBookReviewService,
) {

    @Operation(summary = "도서관내 책 리뷰 조회")
    @GetMapping("/libbooks/{libBookId}")
    fun getBookReviewListInLib(
        @PathVariable libBookId: Long,
        @PageableDefault pageable: Pageable,
    ): ResponseEntity<Page<LibBookReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(libBookReviewService.getReviewListByLibBookId(libBookId, pageable))
    }

    @Operation(summary = "책 평점")
    @GetMapping("/libbooks/{libBookReviewId}/point")
    fun getAverage(
        @PathVariable libBookReviewId: Long,
    ): ResponseEntity<AverageScoreDto>{
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
        libBookReviewService.createBookReviewInLib(libBookId, userId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }


    @Operation(summary = "도서관내 책 리뷰 수정")
    @PatchMapping("/{libBookReviewId}")
    fun updateBookReviewInLib(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody libBookReviewRequest: LibBookReviewRequest,
    ): ResponseEntity<LibBookReviewResponse> {
        val userId = userPrincipal.id
        libBookReviewService.updateBookReviewInLib(libBookReviewId, userId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
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