package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Street(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_STREET) }
        require(value.matches(RegexpMatches.STREET)) { throw InvalidArgumentException(ErrorCodes.INVALID_STREET) }
    }

    fun isEqual(other: Street?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val STREET_MIN_LENGTH = 2
        const val STREET_MAX_LENGTH = 100

        fun fromString(value: String?): Street =
            value?.let { Street(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_STREET)
    }
}
