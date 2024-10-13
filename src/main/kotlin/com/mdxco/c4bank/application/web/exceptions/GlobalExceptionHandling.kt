package com.mdxco.c4bank.application.web.exceptions

import com.mdxco.c4bank.domain.account.exceptions.AccountAlreadyExistsException
import com.mdxco.c4bank.domain.account.exceptions.ErrorCodes
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandling {
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ResponseCode> {
        return ResponseEntity(
            ResponseCode(
                code = ErrorCodes.INTERNAL_SERVER_ERROR.name,
                message = ErrorCodes.INTERNAL_SERVER_ERROR.message
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: RuntimeException): ResponseEntity<ResponseCode> {
        return ResponseEntity(
            ResponseCode(
                code = ErrorCodes.INTERNAL_SERVER_ERROR.name,
                message = ErrorCodes.INTERNAL_SERVER_ERROR.message
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ResponseCodeWithBodyMap> {
        val errors = e.bindingResult.fieldErrors.groupBy({ it.field }, { it.defaultMessage ?: "Invalid value" })

        return ResponseEntity(
            ResponseCodeWithBodyMap(
                body = errors,
                code = ErrorCodes.INVALID_REQUEST_DATA.name,
                message = ErrorCodes.INVALID_REQUEST_DATA.message
            ),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(AccountAlreadyExistsException::class)
    fun handleAccountAlreadyExistsException(e: AccountAlreadyExistsException): ResponseEntity<ResponseCode> {
        return ResponseEntity(
            ResponseCode(
                code = e.code!!.name,
                message = e.message!!
            ),
            HttpStatus.CONFLICT
        )
    }
}