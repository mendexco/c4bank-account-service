package com.mdxco.c4bank.infrastructure.gateways.address

import com.mdxco.c4bank.domain.account.entities.Address
import com.mdxco.c4bank.domain.account.gateways.AddressGateway
import com.mdxco.c4bank.infrastructure.h2.repositories.address.AddressRepository
import com.mdxco.c4bank.infrastructure.h2.repositories.address.models.toModel
import org.springframework.stereotype.Component

@Component
class AddressGatewayImpl(
    private val addressRepository: AddressRepository
) : AddressGateway {
    override fun addAddress(address: Address): Address {
        return addressRepository.saveAndFlush(address.toModel()).toDomain()
    }

    override fun getAddress(postalCode: String): Address? {
        return addressRepository.findAddressByPostalCode(postalCode)?.toDomain()
    }
}