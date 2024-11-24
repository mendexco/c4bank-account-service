package com.mdxco.c4bank.account.domain.address.entities

import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.values.City
import com.mdxco.c4bank.account.domain.address.entities.values.Country
import com.mdxco.c4bank.account.domain.address.entities.values.Neighborhood
import com.mdxco.c4bank.account.domain.address.entities.values.Number
import com.mdxco.c4bank.account.domain.address.entities.values.PostalCode
import com.mdxco.c4bank.account.domain.address.entities.values.State
import com.mdxco.c4bank.account.domain.address.entities.values.Street
import com.mdxco.c4bank.account.domain.address.messaging.AddressProducer

/**
 * Represents an address.
 * @property id the address id
 * @property city the address city
 * @property country the address country
 * @property neighborhood the address neighborhood
 * @property number the address number
 * @property postalCode the address postal code
 * @property state the address state
 * @property street the address street
 */
class Address private constructor(
    val id: String? = null,
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
        listOf(id, city, country, neighborhood, number, postalCode, state, street).all { it.toString().isBlank() }

    /**
     * Verifies if the address is equal to another address.
     * @param other the other address to compare
     * @return true if the addresses are equal, false otherwise
     */
    fun isEqual(other: Address?) = run {
        if (other?.isNullOrBlank() == true) false

        listOf(
            city.isEqual(other!!.city),
            country.isEqual(other.country),
            neighborhood.isEqual(other.neighborhood),
            number.isEqual(other.number),
            postalCode.isEqual(other.postalCode),
            state.isEqual(other.state),
            street.isEqual(other.street)
        ).all { it }
    }

    /**
     * Verifies if the address is equal to another address.
     * @param other the other address to compare
     * @return true if the addresses are equal, false otherwise
     */
    fun isEqual(other: AddressToBeCreated) = isEqual(other.toEntity())

    /**
     * Updates the address if there are changes. If the address is null or blank, it returns the current address.
     * Also posts in a topic a verification if the address is in use and if not, deletes it.
     * @param addressGateway the gateway to save the address
     * @param addressProducer the address producer
     * @param addressUpdates the new address updates
     * @return A pair where the first element is the updated address and the second element is a flag
     * indicating if the address was changed (true) or not (false).
     */
    fun update(
        addressGateway: AddressGateway, addressProducer: AddressProducer, addressUpdates: AddressToBeCreated?
    ) = run {
        if (addressUpdates == null || addressUpdates.isNullOrBlank()) this to false
        if (isEqual(addressUpdates!!)) this to false

        val updatedAddress = create(addressGateway, addressUpdates)

        // Adds to queue a scanning process to check if the old addressFoundById.id is still in use, and if not, delete it
        addressProducer.verifyAddressUses(this.id!!)

        updatedAddress to true
    }

    companion object {
        fun fromModel(
            city: City,
            country: Country,
            id: String,
            neighborhood: Neighborhood,
            number: Number,
            postalCode: PostalCode,
            state: State,
            street: Street,
        ) = Address(
            city = city,
            country = country,
            id = id,
            neighborhood = neighborhood,
            number = number,
            postalCode = postalCode,
            state = state,
            street = street,
        )

        fun fromAddressToBeCreated(
            city: City,
            country: Country,
            neighborhood: Neighborhood,
            number: Number,
            postalCode: PostalCode,
            state: State,
            street: Street,
        ) = Address(
            city = city,
            country = country,
            neighborhood = neighborhood,
            number = number,
            postalCode = postalCode,
            state = state,
            street = street,
        )

        /**
         * Creates an address with the given data. If the address already exists, it returns the existing address.
         * @param addressGateway the gateway to save the address
         * @param addressToBeCreated the address data
         * @return the created address
         */
        fun create(
            addressGateway: AddressGateway,
            addressToBeCreated: AddressToBeCreated,
        ) = run {
            val addressesFoundByPostalCode = addressGateway.getAddressesByPostalCode(addressToBeCreated.postalCode)
            print("addressesFoundByPostalCode: $addressesFoundByPostalCode")

            if (addressesFoundByPostalCode.any { it.isEqual(addressToBeCreated) }) {
                return@run addressesFoundByPostalCode.first { it.isEqual(addressToBeCreated) }
            }

            addressGateway.addAddress(addressToBeCreated.toEntity())
        }

        /**
         * This method checks if an address is in use
         * @param addressGateway the gateway to check if the address is in use
         * @param addressId the address id
         * @return true if the address is in use, false otherwise
         */
        fun isAddressInUse(addressGateway: AddressGateway, addressId: String) =
            addressGateway.checkIfAddressIsInUse(addressId)
    }
}
