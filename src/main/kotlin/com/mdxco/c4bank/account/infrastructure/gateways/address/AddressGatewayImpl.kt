package com.mdxco.c4bank.account.infrastructure.gateways.address

import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.infrastructure.jpa.repositories.AccountRepository
import com.mdxco.c4bank.account.infrastructure.jpa.repositories.AddressRepository
import com.mdxco.c4bank.account.infrastructure.models.address.toModel
import org.springframework.stereotype.Component

@Component
class AddressGatewayImpl(
    private val accountRepository: AccountRepository,
    private val addressRepository: AddressRepository,
) : AddressGateway {
    override fun addAddress(address: Address): Address = addressRepository.saveAndFlush(address.toModel()).toDomain()

    override fun checkIfAddressIsInUse(addressId: String): Boolean = accountRepository.existsByAddressId(addressId)

    override fun getAddress(id: String): Address? = addressRepository.findById(id).orElse(null)?.toDomain()

    override fun getAddressesByPostalCode(postalCode: String): List<Address> =
        addressRepository.findAllAddressByPostalCode(postalCode).map {
            it.toDomain()
        }

    override fun removeUnusedAddress(addressId: String) {
        addressRepository.deleteById(addressId)
    }
}
