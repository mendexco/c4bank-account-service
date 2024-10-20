package com.mdxco.c4bank.account.application.web.requests

import com.mdxco.c4bank.account.commons.constants.RegexpMatches
import com.mdxco.c4bank.account.commons.constants.ResponseMessages
import com.mdxco.c4bank.account.domain.account.entities.Address
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class AccountAddressRequest(
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 2, max = 100, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @Schema(description = "City name", example = "São Paulo")
    var city: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 2, max = 60, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @Schema(description = "Country name", example = "Brazil")
    var country: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 2, max = 100, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @Schema(description = "Neighborhood name", example = "Vila Mariana")
    var neighborhood: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 1, max = 10, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @Schema(description = "Residence number", example = "123")
    var number: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Pattern(regexp = RegexpMatches.POSTAL_CODE, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "Postal code", example = "09990412")
    var postalCode: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Pattern(regexp = RegexpMatches.STATE, message = ResponseMessages.FIELD_INVALID)
    @Schema(description = "State name", example = "São Paulo")
    var state: String,
    @field:NotBlank(message = ResponseMessages.FIELD_NOT_BLANK)
    @field:NotNull(message = ResponseMessages.FIELD_REQUIRED)
    @field:Length(min = 2, max = 100, message = ResponseMessages.FIELD_LENGTH_NOT_VALID)
    @Schema(description = "Street name", example = "Rua Vergueiro")
    var street: String,
) {
    init {
        city = city.trim()
        country = country.trim()
        neighborhood = neighborhood.trim()
        number = number.trim()
        postalCode = postalCode.trim()
        state = state.trim()
        street = street.trim()
    }

    fun toDomain() =
        Address(
            city = city,
            country = country,
            neighborhood = neighborhood,
            number = number,
            postalCode = postalCode,
            state = state,
            street = street,
        )
}
