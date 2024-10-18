package com.mdxco.c4bank.application.web.exceptions

import com.mdxco.c4bank.domain.account.exceptions.ErrorCodes
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity

object ExceptionHelpers {
    open val logger = LoggerFactory.getLogger(GlobalExceptionHandling::class.java)

    fun responseEntityWithErrorCode(errorCode: ErrorCodes): ResponseEntity<ResponseCode> {
        return ResponseEntity(
            ResponseCode(
                code = errorCode.name,
                message = errorCode.message
            ),
            errorCode.status
        )
    }
}