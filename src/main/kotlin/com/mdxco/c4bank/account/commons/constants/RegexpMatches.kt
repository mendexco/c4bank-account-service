package com.mdxco.c4bank.account.commons.constants

object RegexpMatches {
    // '\\p{L}' allows letters from any language, and '\\p{Z}' allows whitespace separators
    const val NAME = "^[\\p{L}\\p{Z}]+$"
    const val PHONE_NUMBER = "^\\d{9,12}$"
    const val TAX_IDENTIFIER = "^\\d{11}$"

    // address related
    const val POSTAL_CODE = "^\\d{8}$"
    const val STATE = "^[A-Z]{2}$"
}