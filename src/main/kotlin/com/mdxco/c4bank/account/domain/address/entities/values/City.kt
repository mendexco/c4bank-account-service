package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class City(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_CITY) }
        require(value.matches(RegexpMatches.CITY)) { throw InvalidArgumentException(ErrorCodes.INVALID_CITY) }
    }

    fun isEqual(other: City?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val CITY_MIN_LENGTH = 2
        const val CITY_MAX_LENGTH = 120

        fun fromString(value: String?): City =
            value?.let { City(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_CITY)
    }
}
