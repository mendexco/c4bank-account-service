package com.mdxco.c4bank.account.domain.address

import com.mdxco.c4bank.account.domain.address.entities.Address

interface AddressGateway {
    fun addAddress(address: Address): Address

    fun checkIfAddressIsInUse(addressId: String): Boolean

    fun getAddress(id: String): Address?

    fun getAddressesByPostalCode(postalCode: String): List<Address>

    fun removeUnusedAddress(addressId: String)
}
