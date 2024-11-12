package com.mdxco.c4bank.account.domain.account.exceptions

import com.mdxco.c4bank.account.domain.utils.ErrorCodes

data class AccountIdInvalidException(
    val code: ErrorCodes? = ErrorCodes.ACCOUNT_ID_INVALID,
) : RuntimeException(code?.message)
