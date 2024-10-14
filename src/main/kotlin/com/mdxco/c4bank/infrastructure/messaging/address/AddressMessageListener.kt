package com.mdxco.c4bank.infrastructure.messaging.address

import com.mdxco.c4bank.domain.account.helpers.AccountHelpers
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service
class AddressMessageListener(
    private val addressService: AddressService,
    private val accountHelpers: AccountHelpers
) {
    @RabbitListener(queues = ["addressQueue"])
    fun processAddressQueue(addressId: String) {
        if (!accountHelpers.isAddressInUse(addressId)) {
            addressService.removeUnusedAddress(addressId)
        }
    }
}