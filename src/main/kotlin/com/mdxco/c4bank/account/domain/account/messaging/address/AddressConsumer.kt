package com.mdxco.c4bank.account.domain.account.messaging.address

interface AddressConsumer {
    fun verifyAddressUses(addressId: Map<String, String>)
}