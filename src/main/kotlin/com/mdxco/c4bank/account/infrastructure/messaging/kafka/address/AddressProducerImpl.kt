package com.mdxco.c4bank.account.infrastructure.messaging.kafka.address

import com.mdxco.c4bank.account.domain.address.messaging.AddressProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class AddressProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, Map<String, Any>>,
) : AddressProducer {
    override fun verifyAddressUses(addressId: String) {
        kafkaTemplate.send("addressQueue", mapOf("addressId" to addressId))
    }
}
