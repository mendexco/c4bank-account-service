package com.mdxco.c4bank.account.domain.account.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class Phone(
    val value: String,
) {
    init {
        require(value.length in PHONE_MIN_LENGTH..PHONE_MAX_LENGTH) {
            throw InvalidArgumentException(
                ErrorCodes.LENGTH_PHONE,
            )
        }
        require(value.matches(Regex(RegexpMatches.PHONE_NUMBER))) { throw InvalidArgumentException(ErrorCodes.INVALID_PHONE) }
    }

    fun isBlank(): Boolean = value.isBlank()

    companion object {
        const val PHONE_MIN_LENGTH = 9
        const val PHONE_MAX_LENGTH = 12

        fun fromString(value: String?): Phone? = value?.let { Phone(it.trim()) }
    }
}
