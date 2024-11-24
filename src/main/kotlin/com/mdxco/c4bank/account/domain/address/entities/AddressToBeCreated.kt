package com.mdxco.c4bank.account.domain.address.entities

import com.mdxco.c4bank.account.domain.address.entities.values.City
import com.mdxco.c4bank.account.domain.address.entities.values.Country
import com.mdxco.c4bank.account.domain.address.entities.values.Neighborhood
import com.mdxco.c4bank.account.domain.address.entities.values.Number
import com.mdxco.c4bank.account.domain.address.entities.values.PostalCode
import com.mdxco.c4bank.account.domain.address.entities.values.State
import com.mdxco.c4bank.account.domain.address.entities.values.Street

class AddressToBeCreated private constructor(
    val city: City,
    val country: Country,
    val neighborhood: Neighborhood,
    val number: Number,
    val postalCode: PostalCode,
    val state: State,
    val street: Street,
) {

    /**
     * Verifies if the address is null or blank.
     * @return true if the address is null or blank, false otherwise
     */
    fun isNullOrBlank() =
        listOf(city, country, neighborhood, number, postalCode, state, street).all { it.toString().isBlank() }

    fun toEntity() = Address.of(
        city = city,
        country = country,
        neighborhood = neighborhood,
        number = number,
        postalCode = postalCode,
        state = state,
        street = street,
    )

    companion object {
        fun of(
            city: String,
            country: String,
            neighborhood: String,
            number: String,
            postalCode: String,
            state: String,
            street: String,
        ) = AddressToBeCreated(
            city = City.fromString(city),
            country = Country.fromString(country),
            neighborhood = Neighborhood.fromString(neighborhood),
            number = Number.fromString(number),
            postalCode = PostalCode.fromString(postalCode),
            state = State.fromString(state),
            street = Street.fromString(street),
        )
    }
}
