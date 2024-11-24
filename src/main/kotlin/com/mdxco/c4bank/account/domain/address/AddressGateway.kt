package com.mdxco.c4bank.account.domain.address

import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.address.entities.values.PostalCode

interface AddressGateway {
    fun addAddress(address: Address): Address

    fun checkIfAddressIsInUse(addressId: String): Boolean

    fun getAddress(id: String): Address?

    fun getAddressesByPostalCode(postalCode: PostalCode): List<Address>

    fun removeUnusedAddress(addressId: String)
}
