package com.mdxco.c4bank.account.domain.account.exceptions

import com.mdxco.c4bank.account.domain.utils.ErrorCodes
import org.springframework.http.HttpStatus

data class InsufficientDataToUpdateException(
    val code: ErrorCodes? = ErrorCodes.INSUFFICIENT_DATA_TO_UPDATE,
    val status: HttpStatus? = ErrorCodes.INSUFFICIENT_DATA_TO_UPDATE.status,
) : RuntimeException(code?.message)
