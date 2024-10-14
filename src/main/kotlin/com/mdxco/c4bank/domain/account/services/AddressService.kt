package com.mdxco.c4bank.domain.account.services

import com.mdxco.c4bank.domain.account.entities.Address
import com.mdxco.c4bank.domain.account.gateways.AddressGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddressService(
    private val addressGateway: AddressGateway
) {
    fun getAddress(id: String): Address? {
        return addressGateway.getAddress(id)
    }

    fun getAddressesByPostalCode(postalCode: String): List<Address> {
        return addressGateway.getAddressesByPostalCode(postalCode)
    }

    @Transactional
    fun addAddress(address: Address): Address {
        val addressesFoundByPostalCode = getAddressesByPostalCode(address.postalCode)

        if (addressesFoundByPostalCode.isEmpty() || addressesFoundByPostalCode.none { it.isEqual(address) }) {
            return addressGateway.addAddress(address)
        }

        return addressesFoundByPostalCode.first { it.isEqual(address) }
    }

    @Transactional
    fun updateAddress(addressFoundById: Address, addressUpdates: Address?): Address {
        if (addressUpdates == null) return addressFoundById
        if (addressFoundById.isEqual(addressUpdates)) return addressFoundById

        // TODO: add to queue a scanning process to check if the old addressFoundById.id is still in use, and if not, delete it

        return addAddress(addressUpdates)
    }
}