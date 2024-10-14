package com.mdxco.c4bank.infrastructure.messaging.address

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class AddressMessageSender(private val rabbitTemplate: RabbitTemplate) {
    fun verifyAddressUses(addressId: String) {
        rabbitTemplate.convertAndSend("addressExchange", "address.routing.key", addressId)
    }
}