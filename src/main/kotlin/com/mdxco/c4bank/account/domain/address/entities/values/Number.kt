package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Number(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_NUMBER) }
        require(value.matches(RegexpMatches.NUMBER)) { throw InvalidArgumentException(ErrorCodes.INVALID_NUMBER) }
    }

    fun isEqual(other: Number?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val NUMBER_MIN_LENGTH = 1
        const val NUMBER_MAX_LENGTH = 10

        fun fromString(value: String?): Number =
            value?.let { Number(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_NUMBER)
    }
}
