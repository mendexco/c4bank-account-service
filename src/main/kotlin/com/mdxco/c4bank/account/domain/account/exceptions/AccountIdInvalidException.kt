package com.mdxco.c4bank.account.domain.account.exceptions

data class AccountIdInvalidException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_ID_INVALID
) : RuntimeException(ErrorCodes.ACCOUNT_ID_INVALID.message)