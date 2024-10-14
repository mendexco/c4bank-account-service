package com.mdxco.c4bank.domain.account.exceptions

data class AddressNotFoundException(
    val code: ErrorCodes? = ErrorCodes.ADDRESS_NOT_FOUND
) : RuntimeException(ErrorCodes.ADDRESS_NOT_FOUND.message)