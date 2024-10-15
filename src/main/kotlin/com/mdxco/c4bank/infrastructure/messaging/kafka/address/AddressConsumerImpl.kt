package com.mdxco.c4bank.infrastructure.messaging.kafka.address

import com.mdxco.c4bank.domain.account.helpers.AccountHelpers
import com.mdxco.c4bank.domain.account.messaging.address.AddressConsumer
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service("AddressConsumerKafkaImpl")
class AddressConsumerImpl(
    private val addressService: AddressService,
    private val accountHelpers: AccountHelpers
) : AddressConsumer {
    @KafkaListener(topics = ["addressQueue"])
    override fun verifyAddressUses(addressId: String) {
        if (!accountHelpers.isAddressInUse(addressId)) {
            addressService.removeUnusedAddress(addressId)
        }
    }
}