package com.mdxco.c4bank.domain.account.services

import com.mdxco.c4bank.domain.account.entities.Address
import com.mdxco.c4bank.domain.account.gateways.AddressGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddressService(
    private val addressGateway: AddressGateway
) {
    fun getAddressByPostalCode(postalCode: String): Address? {
        return addressGateway.getAddress(postalCode)
    }

    @Transactional
    fun addAddress(address: Address): Address {
        return getAddressByPostalCode(address.postalCode) ?: addressGateway.addAddress(address)
    }
}