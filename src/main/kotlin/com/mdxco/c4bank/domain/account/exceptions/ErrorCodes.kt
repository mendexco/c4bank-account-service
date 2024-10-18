package com.mdxco.c4bank.domain.account.exceptions

import org.springframework.http.HttpStatus

enum class ErrorCodes(val message: String, val status: HttpStatus = HttpStatus.BAD_REQUEST) {
    INTERNAL_SERVER_ERROR("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST_DATA("One or more fields were incorrectly informed", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_EXISTS("Account with informed tax identifier already exists", HttpStatus.CONFLICT),
    ACCOUNT_NOT_FOUND("Account not found by informed ID", HttpStatus.NOT_FOUND),
    ACCOUNT_ID_INVALID("Account ID is invalid", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND("Address not found by informed ID", HttpStatus.NOT_FOUND),
    INSUFFICIENT_DATA_TO_UPDATE("No data to update account was provided", HttpStatus.UNPROCESSABLE_ENTITY),
}