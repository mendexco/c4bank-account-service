package com.mdxco.c4bank.account.commons.helpers

import com.mdxco.c4bank.account.domain.utils.ErrorCodes

data class InvalidArgumentException(
    val code: ErrorCodes? = ErrorCodes.INVALID_REQUEST_DATA,
) : RuntimeException(code?.message)
