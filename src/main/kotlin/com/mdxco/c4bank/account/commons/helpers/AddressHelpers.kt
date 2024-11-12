package com.mdxco.c4bank.account.commons.helpers

import com.mdxco.c4bank.account.domain.address.AddressGateway
import org.springframework.stereotype.Component

@Component
class AddressHelpers(
    private val addressGateway: AddressGateway,
) {
    /**
     * This method checks if an address is in use
     * @param addressId the address id
     * @return true if the address is in use, false otherwise
     */
    fun isAddressInUse(addressId: String): Boolean = addressGateway.checkIfAddressIsInUse(addressId)
}
