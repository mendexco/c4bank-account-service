package com.mdxco.c4bank.account.domain.account.exceptions

data class AccountNotFoundException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_NOT_FOUND
) : RuntimeException(ErrorCodes.ACCOUNT_NOT_FOUND.message)