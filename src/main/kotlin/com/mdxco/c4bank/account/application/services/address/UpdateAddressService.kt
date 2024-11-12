package com.mdxco.c4bank.account.application.services.address

import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.address.messaging.AddressProducer
import com.mdxco.c4bank.account.domain.utils.LockingHelpers
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateAddressService(
    private val addAddressService: AddAddressService,
    private val addressProducer: AddressProducer,
) {
    /**
     * Updates the address if there are changes.
     * @param originalAddress The original address.
     * @param addressUpdates The new address updates.
     * @return A pair where the first element is the updated address and the second element is a flag indicating if the address was changed (true) or not (false).
     */
    @Transactional
    fun execute(
        originalAddress: Address,
        addressUpdates: Address?,
    ): Pair<Address, Boolean> {
        if (addressUpdates == null) return originalAddress to false
        if (originalAddress.isEqual(addressUpdates)) return originalAddress to false

        return LockingHelpers.withLock {
            val updatedAddress = addAddressService.execute(addressUpdates)

            // Adds to queue a scanning process to check if the old addressFoundById.id is still in use, and if not, delete it
            addressProducer.verifyAddressUses(originalAddress.id!!)

            return@withLock updatedAddress to true
        }
    }
}
