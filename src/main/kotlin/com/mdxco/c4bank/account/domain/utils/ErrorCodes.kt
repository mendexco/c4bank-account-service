package com.mdxco.c4bank.account.domain.utils

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
import org.springframework.http.HttpStatus

enum class ErrorCodes(
    val message: String,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
) {
    BLANK_CITY(
        "City must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_COUNTRY(
        "Country must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_NAME(
        "Name must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_NEIGHBORHOOD(
        "Neighborhood must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_NUMBER(
        "Number must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_POSTAL_CODE(
        "Postal code must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_STATE(
        "State must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_STREET(
        "Street must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    BLANK_TAX_IDENTIFIER(
        "Tax identifier must not be blank",
        HttpStatus.BAD_REQUEST
    ),
    INTERNAL_SERVER_ERROR(
        "An unexpected error occurred",
        HttpStatus.INTERNAL_SERVER_ERROR
    ),
    INVALID_REQUEST_DATA(
        "One or more fields were incorrectly informed",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_CITY(
        "City must have only letters and length between $CITY_MIN_LENGTH and $CITY_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_COUNTRY(
        "Country must have only letters and length between $COUNTRY_MIN_LENGTH and $COUNTRY_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_NAME(
        "Name must have only letters and length between $NAME_MIN_LENGTH and $NAME_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_NEIGHBORHOOD(
        "Neighborhood must have only letters and length between $NEIGHBORHOOD_MIN_LENGTH and $NEIGHBORHOOD_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_NUMBER(
        "Number must have only letters, digits and length between $NUMBER_MIN_LENGTH and $NUMBER_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_PHONE(
        "Phone must have only digits and length between $PHONE_MIN_LENGTH and $PHONE_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_POSTAL_CODE(
        "Postal code must have only digits and length of $POSTAL_CODE_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_STATE(
        "State must have only letters and length of $STATE_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_STREET(
        "Street must have only letters and length between $STREET_MIN_LENGTH and $STREET_MAX_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    INVALID_TAX_IDENTIFIER(
        "Tax identifier must have only digits and length of $TAX_IDENTIFIER_LENGTH",
        HttpStatus.BAD_REQUEST
    ),
    ACCOUNT_ALREADY_EXISTS(
        "Account with informed tax identifier already exists",
        HttpStatus.CONFLICT
    ),
    ACCOUNT_NOT_FOUND(
        "Account not found by informed ID",
        HttpStatus.NOT_FOUND
    ),
    ACCOUNT_ID_INVALID(
        "Account ID is invalid",
        HttpStatus.BAD_REQUEST
    ),
    ADDRESS_NOT_FOUND(
        "Address not found by informed ID",
        HttpStatus.NOT_FOUND
    ),
    INSUFFICIENT_DATA_TO_UPDATE(
        "No data to update account was provided",
        HttpStatus.UNPROCESSABLE_ENTITY
    ),
}
