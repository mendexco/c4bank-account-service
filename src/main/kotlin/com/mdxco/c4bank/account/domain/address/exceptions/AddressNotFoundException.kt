package com.mdxco.c4bank.account.domain.address.exceptions

import com.mdxco.c4bank.account.domain.utils.ErrorCodes

data class AddressNotFoundException(
    val code: ErrorCodes? = ErrorCodes.ADDRESS_NOT_FOUND,
) : RuntimeException(code?.message)
