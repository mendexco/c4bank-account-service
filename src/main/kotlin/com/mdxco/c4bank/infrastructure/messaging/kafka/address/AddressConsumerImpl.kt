package com.mdxco.c4bank.infrastructure.messaging.kafka.address

import com.mdxco.c4bank.domain.account.exceptions.AccountIdInvalidException
import com.mdxco.c4bank.domain.account.utils.AccountHelpers
import com.mdxco.c4bank.domain.account.messaging.address.AddressConsumer
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AddressConsumerImpl(
    private val addressService: AddressService,
    private val accountHelpers: AccountHelpers
) : AddressConsumer {
    @KafkaListener(topics = ["addressQueue"])
    override fun verifyAddressUses(addressId: Map<String, String>) {
        val mappedAddressId = addressId["addressId"] ?: throw AccountIdInvalidException()
        if (!accountHelpers.isAddressInUse(mappedAddressId)) {
            addressService.removeUnusedAddress(mappedAddressId)
        }
    }
}