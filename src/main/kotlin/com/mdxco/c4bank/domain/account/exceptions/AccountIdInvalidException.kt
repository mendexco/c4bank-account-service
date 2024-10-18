package com.mdxco.c4bank.domain.account.exceptions

data class AccountIdInvalidException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_ID_INVALID
) : RuntimeException(ErrorCodes.ACCOUNT_ID_INVALID.message)