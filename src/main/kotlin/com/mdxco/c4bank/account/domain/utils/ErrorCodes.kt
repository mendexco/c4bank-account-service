package com.mdxco.c4bank.account.domain.utils

import com.mdxco.c4bank.account.domain.account.entities.values.Name.Companion.NAME_MAX_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Name.Companion.NAME_MIN_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Phone.Companion.PHONE_MAX_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.Phone.Companion.PHONE_MIN_LENGTH
import com.mdxco.c4bank.account.domain.account.entities.values.TaxIdentifier.Companion.TAX_IDENTIFIER_MAX_LENGTH
import org.springframework.http.HttpStatus

enum class ErrorCodes(
    val message: String,
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
) {
    BLANK_NAME("Name must not be blank", HttpStatus.BAD_REQUEST),
    BLANK_TAX_IDENTIFIER("Tax identifier must not be blank", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST_DATA("One or more fields were incorrectly informed", HttpStatus.BAD_REQUEST),
    INVALID_NAME("Name must have only letters", HttpStatus.BAD_REQUEST),
    INVALID_PHONE("Phone must have only digits", HttpStatus.BAD_REQUEST),
    INVALID_TAX_IDENTIFIER("Tax identifier must have only digits", HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_EXISTS("Account with informed tax identifier already exists", HttpStatus.CONFLICT),
    ACCOUNT_NOT_FOUND("Account not found by informed ID", HttpStatus.NOT_FOUND),
    ACCOUNT_ID_INVALID("Account ID is invalid", HttpStatus.BAD_REQUEST),
    ADDRESS_NOT_FOUND("Address not found by informed ID", HttpStatus.NOT_FOUND),
    INSUFFICIENT_DATA_TO_UPDATE("No data to update account was provided", HttpStatus.UNPROCESSABLE_ENTITY),
    MAX_LENGTH_TAX_IDENTIFIER("Tax identifier must have $TAX_IDENTIFIER_MAX_LENGTH characters", HttpStatus.BAD_REQUEST),
    LENGTH_NAME("Name must have between $NAME_MIN_LENGTH and $NAME_MAX_LENGTH characters", HttpStatus.BAD_REQUEST),
    LENGTH_PHONE("Phone must have between $PHONE_MIN_LENGTH and $PHONE_MAX_LENGTH characters", HttpStatus.BAD_REQUEST),
}
