package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Neighborhood(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_NEIGHBORHOOD) }
        require(value.matches(RegexpMatches.NEIGHBORHOOD)) { throw InvalidArgumentException(ErrorCodes.INVALID_NEIGHBORHOOD) }
    }

    fun isEqual(other: Neighborhood?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val NEIGHBORHOOD_MIN_LENGTH = 2
        const val NEIGHBORHOOD_MAX_LENGTH = 100

        fun fromString(value: String?): Neighborhood =
            value?.let { Neighborhood(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_NEIGHBORHOOD)
    }
}
