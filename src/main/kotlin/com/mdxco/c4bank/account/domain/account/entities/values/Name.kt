package com.mdxco.c4bank.account.domain.account.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Name(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_NAME) }
        require(value.length in NAME_MIN_LENGTH..NAME_MAX_LENGTH) {
            throw InvalidArgumentException(
                ErrorCodes.LENGTH_NAME,
            )
        }
        require(value.matches(Regex(RegexpMatches.NAME))) { throw InvalidArgumentException(ErrorCodes.INVALID_NAME) }
    }

    fun isBlank(): Boolean = value.isBlank()

    companion object {
        const val NAME_MIN_LENGTH = 2
        const val NAME_MAX_LENGTH = 120

        fun fromString(value: String?): Name = value?.let { Name(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_NAME)
    }
}
