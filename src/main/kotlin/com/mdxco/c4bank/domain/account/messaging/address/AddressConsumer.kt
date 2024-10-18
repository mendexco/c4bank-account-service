package com.mdxco.c4bank.domain.account.messaging.address

interface AddressConsumer {
    fun verifyAddressUses(addressId: Map<String, String>)
}