package com.mdxco.c4bank.account.commons.constants

import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier.Companion.TAX_IDENTIFIER_MAX_LENGTH

object RegexpMatches {
    // '\\p{L}' allows letters from any language, and '\\p{Z}' allows whitespace separators
    const val NAME = "^[\\p{L}\\p{Z}]+$"
    const val PHONE_NUMBER = "^\\d{9,12}$"
    const val TAX_IDENTIFIER = "^\\d{$TAX_IDENTIFIER_MAX_LENGTH}$"

    // address related
    const val POSTAL_CODE = "^\\d{8}$"
    const val STATE = "^[A-Z]{2}$"
}
