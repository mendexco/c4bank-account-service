package com.mdxco.c4bank.account.domain.account.exceptions

data class AddressNotFoundException(
    val code: ErrorCodes? = ErrorCodes.ADDRESS_NOT_FOUND
) : RuntimeException(ErrorCodes.ADDRESS_NOT_FOUND.message)