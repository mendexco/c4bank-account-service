package com.mdxco.c4bank.account.application.services.address

import com.mdxco.c4bank.account.domain.address.AddressGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveUnusedAddressService(
    private val addressGateway: AddressGateway,
) {
    /**
     * Removes an address from the database if it is not being used by any account.
     * @param addressId the id of the address to be removed
     * @return the address that was removed
     */
    @Transactional
    fun execute(addressId: String) {
        addressGateway.removeUnusedAddress(addressId)
    }
}
