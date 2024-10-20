package com.mdxco.c4bank.account.domain.account.exceptions

data class AccountAlreadyExistsException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_ALREADY_EXISTS,
) : RuntimeException(ErrorCodes.ACCOUNT_ALREADY_EXISTS.message)
