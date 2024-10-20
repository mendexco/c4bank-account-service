package com.mdxco.c4bank.account.infrastructure.gateways.address

import com.mdxco.c4bank.account.domain.account.entities.Address
import com.mdxco.c4bank.account.domain.account.gateways.AddressGateway
import com.mdxco.c4bank.account.infrastructure.jpa.repositories.AddressRepository
import com.mdxco.c4bank.account.infrastructure.models.address.toModel
import org.springframework.stereotype.Component

@Component
class AddressGatewayImpl(
    private val addressRepository: AddressRepository
) : AddressGateway {
    override fun addAddress(address: Address): Address {
        return addressRepository.saveAndFlush(address.toModel()).toDomain()
    }

    override fun getAddress(id: String): Address? {
        return addressRepository.findById(id).orElse(null)?.toDomain()
    }

    override fun getAddressesByPostalCode(postalCode: String): List<Address> {
        return addressRepository.findAllAddressByPostalCode(postalCode).map { it.toDomain() }
    }

    override fun removeUnusedAddress(addressId: String) {
        addressRepository.deleteById(addressId)
    }
}