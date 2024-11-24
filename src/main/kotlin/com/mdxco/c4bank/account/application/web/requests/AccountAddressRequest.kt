package com.mdxco.c4bank.account.application.web.requests

import com.mdxco.c4bank.account.domain.address.entities.AddressToBeCreated
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
import io.swagger.v3.oas.annotations.media.Schema

data class AccountAddressRequest(
    @Schema(
        description = "City name",
        example = "São Paulo",
        minLength = CITY_MIN_LENGTH,
        maxLength = CITY_MAX_LENGTH
    ) var city: String,
    @Schema(
        description = "Country name",
        example = "Brazil",
        minLength = COUNTRY_MIN_LENGTH,
        maxLength = COUNTRY_MAX_LENGTH
    ) var country: String,
    @Schema(
        description = "Neighborhood name",
        example = "Vila Mariana",
        minLength = NEIGHBORHOOD_MIN_LENGTH,
        maxLength = NEIGHBORHOOD_MAX_LENGTH
    ) var neighborhood: String,
    @Schema(
        description = "Residence number",
        example = "123",
        minLength = NUMBER_MIN_LENGTH,
        maxLength = NUMBER_MAX_LENGTH
    ) var number: String,
    @Schema(
        description = "Postal code",
        example = "09990412",
        minLength = POSTAL_CODE_LENGTH,
        maxLength = POSTAL_CODE_LENGTH
    ) var postalCode: String,
    @Schema(
        description = "State abbreviation",
        example = "SP",
        minLength = STATE_LENGTH,
        maxLength = STATE_LENGTH
    ) var state: String,
    @Schema(
        description = "Street name",
        example = "Rua Vergueiro",
        minLength = STREET_MIN_LENGTH,
        maxLength = STREET_MAX_LENGTH
    ) var street: String,
) {
    fun toDomain() = AddressToBeCreated.of(
        city = city,
        country = country,
        neighborhood = neighborhood,
        number = number,
        postalCode = postalCode,
        state = state,
        street = street,
    )
}
