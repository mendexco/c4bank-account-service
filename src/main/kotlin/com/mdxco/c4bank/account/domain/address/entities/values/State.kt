package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class State(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_STATE) }
        require(value.matches(RegexpMatches.STATE)) { throw InvalidArgumentException(ErrorCodes.INVALID_STATE) }
    }

    fun isEqual(other: State?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val STATE_LENGTH = 2

        fun fromString(value: String?): State =
            value?.let { State(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_STATE)
    }
}
