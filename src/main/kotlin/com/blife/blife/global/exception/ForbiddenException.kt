package com.blife.blife.global.exception

class ForbiddenException : CustomException {


    constructor(errorCode: ErrorCode) : super(errorCode = errorCode, message = errorCode.message)

    constructor(message: String, errorCode: ErrorCode) : super(message = message, errorCode = errorCode)

    constructor(payload: Any, errorCode: ErrorCode) : super(
        payload = payload,
        errorCode = errorCode,
        message = errorCode.message
    )

    constructor(message: String, payload: Any, errorCode: ErrorCode) : super(
        message,
        errorCode,
        payload
    )

    override fun log() {
        super.logger.error("대상 Entity를 찾지 못하였습니다.")
        message.let { super.logger.info(it) }
        super.logger.info("payload = $payload")
    }

}