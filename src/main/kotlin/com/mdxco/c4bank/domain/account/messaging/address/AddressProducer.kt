package com.mdxco.c4bank.domain.account.messaging.address

interface AddressProducer {
    fun verifyAddressUses(addressId: String)
}