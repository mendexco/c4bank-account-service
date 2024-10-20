package com.mdxco.c4bank.account.domain.account.exceptions

import org.springframework.http.HttpStatus

data class InsufficientDataToUpdateException(
    val code: ErrorCodes? = ErrorCodes.INSUFFICIENT_DATA_TO_UPDATE,
    val status: HttpStatus? = ErrorCodes.INSUFFICIENT_DATA_TO_UPDATE.status,
) : RuntimeException(ErrorCodes.INSUFFICIENT_DATA_TO_UPDATE.message)
