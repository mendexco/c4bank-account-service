package com.mdxco.c4bank.account.commons.constants

import com.mdxco.c4bank.account.domain.account.entities.values.Name.Companion.NAME_MAX_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Name.Companion.NAME_MIN_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Phone.Companion.PHONE_MAX_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Phone.Companion.PHONE_MIN_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier.Companion.TAX_IDENTIFIER_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.City.Companion.CITY_MAX_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.City.Companion.CITY_MIN_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Country.Companion.COUNTRY_MAX_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Country.Companion.COUNTRY_MIN_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Neighborhood.Companion.NEIGHBORHOOD_MAX_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Neighborhood.Companion.NEIGHBORHOOD_MIN_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Number.Companion.NUMBER_MAX_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Number.Companion.NUMBER_MIN_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.PostalCode.Companion.POSTAL_CODE_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.State.Companion.STATE_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Street.Companion.STREET_MAX_LENGTH
import com.mdxco.c4bank.account.domain.address.entities.values.Street.Companion.STREET_MIN_LENGTH

object RegexpMatches {
    // '\\p{L}' allows letters from any language, and '\\p{Z}' allows whitespace separators
    val NAME = Regex("^[\\p{L}\\p{Z}]{$NAME_MIN_LENGTH,$NAME_MAX_LENGTH}$")
    val PHONE_NUMBER = Regex("^\\d{$PHONE_MIN_LENGTH,$PHONE_MAX_LENGTH}$")
    val TAX_IDENTIFIER = Regex("^\\d{$TAX_IDENTIFIER_LENGTH}$")

    // address related
    val CITY = Regex("^[\\p{L}\\p{N}\\p{Z}]{$CITY_MIN_LENGTH,$CITY_MAX_LENGTH}$")
    val COUNTRY = Regex("^[\\p{L}\\p{N}\\p{Z}]{$COUNTRY_MIN_LENGTH,$COUNTRY_MAX_LENGTH}$")
    val NEIGHBORHOOD = Regex("^[\\p{L}\\p{Z}]{$NEIGHBORHOOD_MIN_LENGTH,$NEIGHBORHOOD_MAX_LENGTH}$")
    val NUMBER = Regex("^[\\p{L}\\p{N}\\p{Z}]{$NUMBER_MIN_LENGTH,$NUMBER_MAX_LENGTH}$")
    val POSTAL_CODE = Regex("^\\d{$POSTAL_CODE_LENGTH}$")
    val STATE = Regex("^[A-Z]{$STATE_LENGTH}$")
    val STREET = Regex("^[\\p{L}\\p{N}\\p{Z}]{$STREET_MIN_LENGTH,$STREET_MAX_LENGTH}$")
}
