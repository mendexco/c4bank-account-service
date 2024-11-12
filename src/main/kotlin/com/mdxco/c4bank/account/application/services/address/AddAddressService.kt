package com.mdxco.c4bank.account.application.services.address

import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddAddressService(
    private val addressGateway: AddressGateway,
    private val getAddressService: GetAddressService,
) {
    /**
     * Adds an address to the database if it does not exist yet.
     * If the address already exists, it returns the existing address.
     * @param address the address to be added
     * @return the address that was added or the existing address
     */
    @Transactional
    fun execute(address: Address): Address {
        return LockingHelpers.withLock {
            val addressesFoundByPostalCode = getAddressService.byPostalCode(address.postalCode)

            if (addressesFoundByPostalCode.isEmpty() || addressesFoundByPostalCode.none { it.isEqual(address) }) {
                return@withLock addressGateway.addAddress(address)
            }

            return@withLock addressesFoundByPostalCode.first { it.isEqual(address) }
        }
    }
}
