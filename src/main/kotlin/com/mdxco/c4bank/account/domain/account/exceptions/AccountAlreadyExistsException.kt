package com.mdxco.c4bank.account.domain.account.exceptions

import com.mdxco.c4bank.account.domain.utils.ErrorCodes

data class AccountAlreadyExistsException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_ALREADY_EXISTS,
) : RuntimeException(code?.message)
