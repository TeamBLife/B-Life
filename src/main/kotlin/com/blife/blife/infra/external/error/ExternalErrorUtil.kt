package com.blife.blife.infra.external.error

import com.blife.blife.domain.library.application.error.ExternalErrorCode
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpResponse

open class ExternalErrorUtil {
	/**
	 * @param code HttpStatus 값을 입력하여 주십시오.
	 */
	private fun getErrorCode(code: Int): ExternalErrorCode = when (code) {
		429 -> ExternalErrorCode.OVER_QUARTER
		else -> ExternalErrorCode.UNKNOWN_ERROR
	}

	protected fun errorHandler(errorObject: ExternalErrorObject): (HttpRequest, ClientHttpResponse) -> Unit {
		return { _, response ->
			errorObject.errorCode = getErrorCode(response.statusCode.value())
		}
	}
}