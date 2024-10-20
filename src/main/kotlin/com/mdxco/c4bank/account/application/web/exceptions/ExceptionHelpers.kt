package com.mdxco.c4bank.account.application.web.exceptions

import com.mdxco.c4bank.account.domain.account.exceptions.ErrorCodes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity

object ExceptionHelpers {
    val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandling::class.java)

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