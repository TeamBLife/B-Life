package com.blife.blife.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

	@ExceptionHandler(CustomException::class)
	fun customExceptionHandler(e: CustomException) {
		e.log()
		return e.getErrorResponse()
			.let { ResponseEntity.status(e.errorCode.httpStatus).body(it) }
	}
}