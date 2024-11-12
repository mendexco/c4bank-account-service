package com.mdxco.c4bank.account.application.services.address

import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.Address
import org.springframework.stereotype.Service

@Service
class GetAddressService(
    private val addressGateway: AddressGateway,
) {
    /**
     * Gets an address by its id.
     * @param id the id of the address to be retrieved
     * @return the address that was retrieved
     */
    fun byId(id: String): Address? = addressGateway.getAddress(id)

    /**
     * Gets all addresses in the database.
     * @return a list of all addresses in the database
     */
    fun byPostalCode(postalCode: String): List<Address> = addressGateway.getAddressesByPostalCode(postalCode)
}
