package com.mdxco.c4bank.account.domain.address.entities.values

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.helpers.InvalidArgumentException
import com.mdxco.c4bank.account.domain.utils.ErrorCodes

@JvmInline
value class PostalCode(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { throw InvalidArgumentException(ErrorCodes.BLANK_POSTAL_CODE) }
        require(value.matches(RegexpMatches.POSTAL_CODE)) { throw InvalidArgumentException(ErrorCodes.INVALID_POSTAL_CODE) }
    }

    fun isEqual(other: PostalCode?): Boolean {
        if (other == null) return false
        return this.value == other.value
    }

    companion object {
        const val POSTAL_CODE_LENGTH = 8

        fun fromString(value: String?): PostalCode =
            value?.let { PostalCode(it.trim()) } ?: throw InvalidArgumentException(ErrorCodes.BLANK_POSTAL_CODE)
    }
}
