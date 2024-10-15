package com.mdxco.c4bank.infrastructure.messaging.kafka.address

import com.mdxco.c4bank.domain.account.messaging.address.AddressProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service("AddressProducerKafkaImpl")
class AddressProducerImpl(private val kafkaTemplate: KafkaTemplate<String, String>) : AddressProducer {
    override fun verifyAddressUses(addressId: String) {
        kafkaTemplate.send("addressQueue", addressId)
    }
}