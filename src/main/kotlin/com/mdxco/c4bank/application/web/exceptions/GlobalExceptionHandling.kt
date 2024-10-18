package com.mdxco.c4bank.application.web.exceptions

import com.mdxco.c4bank.domain.account.exceptions.AccountAlreadyExistsException
import com.mdxco.c4bank.domain.account.exceptions.AccountNotFoundException
import com.mdxco.c4bank.domain.account.exceptions.AddressNotFoundException
import com.mdxco.c4bank.domain.account.exceptions.ErrorCodes
import com.mdxco.c4bank.domain.account.exceptions.InsufficientDataToUpdateException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandling {
//    @ExceptionHandler(Exception::class)
//    fun handleException(e: Exception): ResponseEntity<ResponseCode> {
//        ExceptionHelpers.logger.error(e.stackTraceToString())
//        return ResponseEntity(
//            ResponseCode(
//                code = ErrorCodes.INTERNAL_SERVER_ERROR.name,
//                message = ErrorCodes.INTERNAL_SERVER_ERROR.message
//            ),
//            HttpStatus.INTERNAL_SERVER_ERROR
//        )
//    }
//
//    @ExceptionHandler(RuntimeException::class)
//    fun handleException(e: RuntimeException): ResponseEntity<ResponseCode> {
//        ExceptionHelpers.logger.error(e.stackTraceToString())
//        return ResponseEntity(
//            ResponseCode(
//                code = ErrorCodes.INTERNAL_SERVER_ERROR.name,
//                message = ErrorCodes.INTERNAL_SERVER_ERROR.message
//            ),
//            HttpStatus.INTERNAL_SERVER_ERROR
//        )
//    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ResponseCodeWithBodyMap> {
        ExceptionHelpers.logger.error(e.stackTraceToString())

        val errors = e.bindingResult.fieldErrors.groupBy({ it.field }, { it.defaultMessage ?: "Invalid value" })

        return ResponseEntity(
            ResponseCodeWithBodyMap(
                body = errors,
                code = ErrorCodes.INVALID_REQUEST_DATA.name,
                message = ErrorCodes.INVALID_REQUEST_DATA.message
            ),
            ErrorCodes.INVALID_REQUEST_DATA.status
        )
    }

    @ExceptionHandler(AccountAlreadyExistsException::class)
    fun handleAccountAlreadyExistsException(e: AccountAlreadyExistsException): ResponseEntity<ResponseCode> {
        ExceptionHelpers.logger.error(e.stackTraceToString())

        return ExceptionHelpers.responseEntityWithErrorCode(e.code!!)
    }

    @ExceptionHandler(AccountNotFoundException::class)
    fun handleAccountNotFoundException(e: AccountNotFoundException): ResponseEntity<ResponseCode> {
        ExceptionHelpers.logger.error(e.stackTraceToString())

        return ExceptionHelpers.responseEntityWithErrorCode(e.code!!)
    }

    @ExceptionHandler(AddressNotFoundException::class)
    fun handleAddressNotFoundException(e: AddressNotFoundException): ResponseEntity<ResponseCode> {
        ExceptionHelpers.logger.error(e.stackTraceToString())

        return ExceptionHelpers.responseEntityWithErrorCode(e.code!!)
    }

    @ExceptionHandler(InsufficientDataToUpdateException::class)
    fun handleInsufficientDataToUpdateException(e: InsufficientDataToUpdateException): ResponseEntity<ResponseCode> {
        ExceptionHelpers.logger.error(e.stackTraceToString())

        return ExceptionHelpers.responseEntityWithErrorCode(e.code!!)
    }
}