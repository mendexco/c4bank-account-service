package com.mdxco.c4bank.domain.account.gateways

import com.mdxco.c4bank.domain.account.entities.Address

interface AddressGateway {
    fun addAddress(address: Address): Address
    fun getAddress(id: String): Address?
    fun getAddressesByPostalCode(postalCode: String): List<Address>
    fun removeUnusedAddress(addressId: String)
}