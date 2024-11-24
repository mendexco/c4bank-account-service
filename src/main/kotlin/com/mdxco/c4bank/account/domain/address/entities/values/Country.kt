package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Country(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_COUNTRY) }
        require(value.matches(RegexpMatches.COUNTRY)) { throw InvalidArgumentException(ErrorCodes.INVALID_COUNTRY) }
    }

    fun isEqual(other: Country?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val COUNTRY_MIN_LENGTH = 2
        const val COUNTRY_MAX_LENGTH = 60

        fun fromString(value: String?): Country =
            value?.let { Country(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_COUNTRY)
    }
}
