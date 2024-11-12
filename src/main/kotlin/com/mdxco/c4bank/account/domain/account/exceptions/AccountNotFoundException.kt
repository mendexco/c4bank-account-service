package com.mdxco.c4bank.account.domain.account.exceptions

import com.mdxco.c4bank.account.domain.utils.ErrorCodes

data class AccountNotFoundException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_NOT_FOUND,
) : RuntimeException(code?.message)
