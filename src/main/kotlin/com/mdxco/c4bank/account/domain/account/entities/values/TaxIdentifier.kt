package com.mdxco.c4bank.account.domain.account.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class TaxIdentifier(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_TAX_IDENTIFIER) }
        require(value.length == TAX_IDENTIFIER_MAX_LENGTH) { throw InvalidArgumentException(ErrorCodes.MAX_LENGTH_TAX_IDENTIFIER) }
        require(value.matches(Regex(RegexpMatches.TAX_IDENTIFIER))) { throw InvalidArgumentException(ErrorCodes.INVALID_TAX_IDENTIFIER) }
    }

    companion object {
        const val TAX_IDENTIFIER_MAX_LENGTH = 11

        fun fromString(value: String?): TaxIdentifier =
            value?.let { TaxIdentifier(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_TAX_IDENTIFIER)
    }
}
