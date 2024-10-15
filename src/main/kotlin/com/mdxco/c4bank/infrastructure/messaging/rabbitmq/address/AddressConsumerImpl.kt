package com.mdxco.c4bank.infrastructure.messaging.rabbitmq.address

import com.mdxco.c4bank.domain.account.helpers.AccountHelpers
import com.mdxco.c4bank.domain.account.messaging.address.AddressConsumer
import com.mdxco.c4bank.domain.account.services.AddressService
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service

@Service("AddressConsumerRabbitMQImpl")
class AddressConsumerImpl(
    private val addressService: AddressService,
    private val accountHelpers: AccountHelpers
) : AddressConsumer {
    @RabbitListener(queues = ["addressQueue"])
    override fun verifyAddressUses(addressId: String) {
        if (!accountHelpers.isAddressInUse(addressId)) {
            addressService.removeUnusedAddress(addressId)
        }
    }
}