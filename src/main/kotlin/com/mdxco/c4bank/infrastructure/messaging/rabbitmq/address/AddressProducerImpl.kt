package com.mdxco.c4bank.infrastructure.messaging.rabbitmq.address

import com.mdxco.c4bank.domain.account.messaging.address.AddressProducer
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service("AddressProducerRabbitMQImpl")
class AddressProducerImpl(private val rabbitTemplate: RabbitTemplate) : AddressProducer {
    override fun verifyAddressUses(addressId: String) {
        rabbitTemplate.convertAndSend("addressExchange", "address.routing.key", addressId)
    }
}