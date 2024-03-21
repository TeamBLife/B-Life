package com.blife.blife.domain.library.controller

import com.blife.blife.domain.library.application.LibraryUseCase
import com.blife.blife.domain.library.controller.dto.AddLibBookRequest
import com.blife.blife.domain.library.controller.dto.LibraryResponse
import com.blife.blife.domain.library.controller.dto.UpdateLibBookRequest
import com.blife.blife.domain.library.controller.dto.UpdateLibraryRequest
import com.blife.blife.domain.library.service.LibraryService
import com.blife.blife.global.security.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/libraries")
class LibraryController(
	private val libraryUseCase: LibraryUseCase,
	private val libraryService: LibraryService
) {
	@PreAuthorize("hasRole('OWNER')")
	@PostMapping("/{libId}/libbook")
	fun addLibBook(
		@PathVariable libId: Long,
		@RequestBody addLibBookRequest: AddLibBookRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<String> {
		return libraryUseCase.addLibBook(
			addLibBookRequest.isbn13,
			libId,
			addLibBookRequest.totalBookCount,
			userPrincipal.id
		).let { ResponseEntity.status(HttpStatus.CREATED).body("Success") }
	}

	@PreAuthorize("hasRole('OWNER')")
	@PostMapping("/register")
	fun registerLibrary(@AuthenticationPrincipal userPrincipal: UserPrincipal, @RequestParam libId: Long) {
		libraryUseCase.registerLibrary(libId, userPrincipal.id)
	}

	@GetMapping
	fun getLibraryList(
		@RequestParam(defaultValue = "1", required = false) page: Long,
		@RequestParam(defaultValue = "10", required = false) pageSize: Long
	): ResponseEntity<List<LibraryResponse>> {
		return libraryService.searchLibrary(page = page, pageSize = pageSize)
			.map { LibraryResponse.from(it) }
			.let { ResponseEntity.status(HttpStatus.OK).body(it) }
	}

	@GetMapping("/search")
	fun searchLibraryList(
		@RequestParam(required = false) regionCode: Long?,
		@RequestParam(required = false) libName: String?,
		@RequestParam(required = false, defaultValue = "1") page: Long,
		@RequestParam(required = false, defaultValue = "10") pageSize: Long,
	): ResponseEntity<List<LibraryResponse>> {
		return libraryService.searchLibrary(regionCode, libName, page, pageSize)
			.map { LibraryResponse.from(it) }
			.let { ResponseEntity.status(HttpStatus.OK).body(it) }
	}

	@PreAuthorize("hasRole('OWNER')")
	@PatchMapping("/{libId}")
	fun updateLibrary(
		@PathVariable libId: Long,
		@RequestBody updateLibraryRequest: UpdateLibraryRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<String> {
		return libraryUseCase.updateLibrary(updateLibraryRequest, libId, userPrincipal.id)
			.let { ResponseEntity.status(HttpStatus.OK).body("Success") }
	}

	@PreAuthorize("hasRole('OWNER')")
	@PatchMapping("/libbooks/{libBookId}")
	fun updateLibBook(
		@PathVariable libBookId: Long,
		@RequestBody updateLibBookRequest: UpdateLibBookRequest,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<String> {
		return libraryUseCase.updateLibBook(updateLibBookRequest, libBookId, userPrincipal.id)
			.let { ResponseEntity.status(HttpStatus.OK).body("Success") }
	}

	@PreAuthorize("hasRole('OWNER')")
	@DeleteMapping("/libbooks/{libBookId}")
	fun deleteLibBook(
		@PathVariable libBookId: Long,
		@AuthenticationPrincipal userPrincipal: UserPrincipal
	): ResponseEntity<String> {
		return libraryUseCase.deleteLibBook(libBookId, userPrincipal.id)
			.let { ResponseEntity.status(HttpStatus.NO_CONTENT).body("Success") }
	}
}