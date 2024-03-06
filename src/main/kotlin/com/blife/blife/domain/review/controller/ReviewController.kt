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
class ReviewController (
    private val reviewService : ReviewService
){
    @Operation(summary = "책 리뷰 조회")
    @GetMapping("/{bookId}")
    fun getBookReview(
        @PathVariable bookId : Long,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Page<BookReviewResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewByBookId(bookId, pageable))
    }
    @Operation(summary = "도서관내 책 리뷰 조회")
    @GetMapping("/{libBookId}")
    fun getBookReviewInLib(
        @PathVariable libBookId: Long,
        @PageableDefault pageable: Pageable
    ): ResponseEntity<Page<LibBookReviewResponse>>
    {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewByLibBookId(libBookId, pageable))
    }
    @Operation(summary = "책 리뷰 생성")
    @PostMapping("/{bookId}")
    fun createBookReview(
        @PathVariable bookId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        bookReviewRequest: BookReviewRequest
    ) : ResponseEntity<BookReviewResponse>{
        val userId = userPrincipal.id
        reviewService.createBookReview(bookId,userId, bookReviewRequest,)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
    @Operation(summary = "도서관내 책 리뷰 생성")
    @PostMapping("/{libraryId}/{libBookId}")
    fun createBookReviewInLib(
        @PathVariable libBookId: Long,
        @PathVariable libraryId : Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        libBookReviewRequest: LibBookReviewRequest
    ): ResponseEntity<LibBookReviewResponse>{
        val userId = userPrincipal.id
        reviewService.createBookReviewInLib(bookId,  libraryId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
    @Operation(summary = "책 리뷰 수정")
    @PatchMapping("{bookId}/{bookReviewId}")
    fun updateBookReview(
        @PathVariable bookId : Long,
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        bookReviewRequest: BookReviewRequest,
    ) : ResponseEntity<BookReviewResponse>{
        val userId = userPrincipal.id
        reviewService.updateBookReview(bookId, bookReviewId, bookReviewRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
    @Operation(summary = "도서관내 책 리뷰 수정")
    @PatchMapping("/{libBookId}/{libBookReviewId}")
    fun updateBookReviewInLib(
        @PathVariable libBookId : Long,
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        libBookReviewRequest: LibBookReviewRequest
    ): ResponseEntity<LibBookReviewResponse>{
        val userId = userPrincipal.id
        reviewService.updateBookReviewInLib(libBookId,libBookReviewId, libBookReviewRequest)
        return ResponseEntity.status(HttpStatus.OK).build()
    }
    @Operation(summary = "책 리뷰 삭제")
    @DeleteMapping("/{bookReviewId}")
    fun deleteReview(
        @PathVariable bookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit>{
        val userId = userPrincipal.id
        reviewService.deleteReview(bookReviewId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
    @Operation(summary = "도서관 내 책 리뷰 삭제")
    @DeleteMapping("/libBookReviewId}")
    fun deleteLibBookReview(
        @PathVariable libBookReviewId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
    ): ResponseEntity<Unit>{
        val userId = userPrincipal.id
        reviewService.deleteLibBookReview(libBookReviewId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}