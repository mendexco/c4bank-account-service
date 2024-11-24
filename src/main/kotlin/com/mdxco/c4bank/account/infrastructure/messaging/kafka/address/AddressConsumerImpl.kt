package com.mdxco.c4bank.account.infrastructure.messaging.kafka.address

import com.mdxco.c4bank.account.application.services.address.RemoveUnusedAddressService
import com.mdxco.c4bank.account.domain.account.exceptions.AccountIdInvalidException
import com.mdxco.c4bank.account.domain.address.AddressGateway
import com.mdxco.c4bank.account.domain.address.entities.Address
import com.mdxco.c4bank.account.domain.address.messaging.AddressConsumer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AddressConsumerImpl(
    private val addressGateway: AddressGateway,
    private val removeUnusedAddressService: RemoveUnusedAddressService,
) : AddressConsumer {
    @KafkaListener(topics = ["addressQueue"])
    override fun verifyAddressUses(addressId: Map<String, String>) {
        val mappedAddressId = addressId["addressId"] ?: throw AccountIdInvalidException()
        if (!Address.isAddressInUse(addressGateway, mappedAddressId)) {
            removeUnusedAddressService.execute(mappedAddressId)
        }
    }
}
